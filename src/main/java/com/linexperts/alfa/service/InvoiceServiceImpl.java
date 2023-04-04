package com.linexperts.alfa.service;

import com.linexperts.alfa.dto.InvoiceInputDto;
import com.linexperts.alfa.dto.InvoiceOutputDto;
import com.linexperts.alfa.dto.Status;
import com.linexperts.alfa.entity.Invoice;
import com.linexperts.alfa.entity.Policy;
import com.linexperts.alfa.repository.InvoiceRepository;
import com.linexperts.alfa.repository.PolicyRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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
  public void createInvoice(InvoiceInputDto invoiceDto) {
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

  @Override
  public InvoiceOutputDto getInvoice(UUID invoiceId) {
    var invoiceOptional = invoiceRepository.findById(invoiceId);

    if (invoiceOptional.isPresent()){
      var invoice = invoiceOptional.get();
      return mapToInvoiceDto(invoice);
    }

    return InvoiceOutputDto
        .builder().build();
  }

  @Override
  public List<InvoiceOutputDto> getAllInvoices() {
    var invoiceList = invoiceRepository.findAll();

    var invoiceDtoList = new ArrayList<InvoiceOutputDto>();

    invoiceList.stream().forEach(
        invoice -> invoiceDtoList.add(mapToInvoiceDto(invoice))
    );

    return invoiceDtoList;
  }

  private InvoiceOutputDto mapToInvoiceDto(Invoice invoice) {
    ArrayList<UUID> policies = new ArrayList<>();
    invoice.getPolicies().stream().forEach(p -> policies.add(p.getId()));
    return InvoiceOutputDto
        .builder()
        .status(Status.valueOf(invoice.getStatus()))
        .policiesIdsList(policies)
        .customerId(invoice.getCustomerId())
        .dateCreated(invoice.getCreatedDate())
        .cost(invoice.getCost())
        .build();
  }
}
