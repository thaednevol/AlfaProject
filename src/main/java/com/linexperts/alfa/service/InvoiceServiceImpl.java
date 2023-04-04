package com.linexperts.alfa.service;

import com.linexperts.alfa.dto.InvoiceDto;
import com.linexperts.alfa.entity.Invoice;
import com.linexperts.alfa.entity.Policy;
import com.linexperts.alfa.repository.InvoiceRepository;
import com.linexperts.alfa.repository.PolicyRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

  @Autowired
  private InvoiceRepository invoiceRepository;

  @Autowired
  private PolicyRepository policyRepository;

  @Override
  public void createInvoice(InvoiceDto invoiceDto) {
    AtomicReference<Float> cost = new AtomicReference<>(0f);
    Set<Policy> policySet = new HashSet<>();
    invoiceDto.getPoliciesIdsList().stream().forEach(
        policy -> policyRepository.findById(policy)
            .ifPresentOrElse(
                policy1 -> {
                  cost.set(cost.get() + policy1.getCost());
                  policySet.add(policy1);
                }, () -> {
                  throw new RuntimeException();
                })
            );

    var invoice = Invoice.builder()
        .cost(cost.get())
        .customerId(invoiceDto.getCustomerId())
        .status(invoiceDto.getStatus().name())
        .createdDate(LocalDate.now())
        .policies(policySet)
        .build();

    invoiceRepository.save(invoice);
  }
}
