package com.linexperts.alfa.service;

import static org.junit.jupiter.api.Assertions.*;

import com.linexperts.alfa.dto.InvoiceInputDto;
import com.linexperts.alfa.dto.Status;
import com.linexperts.alfa.entity.Invoice;
import com.linexperts.alfa.entity.Policy;
import com.linexperts.alfa.repository.InvoiceRepository;
import com.linexperts.alfa.repository.PolicyRepository;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InvoiceServiceImplTest {

  @Mock
  private InvoiceRepository invoiceRepository;

  @Mock
  private PolicyRepository policyRepository;

  @InjectMocks
  private InvoiceServiceImpl invoiceServiceImpl;

  @Test
  void createInvoice() {
    var invoiceDto = InvoiceInputDto.builder().customerId("customerId").policiesIdsList(List.of(
        UUID.fromString("e31a2114-d247-11ed-afa1-0242ac120002"))).status(Status.PAID).build();

    var policy = Policy.builder().cost(100f).name("Policy Test").description("Test").id(UUID.fromString("e31a2114-d247-11ed-afa1-0242ac120002")).build();

    Mockito.when(policyRepository.findById(UUID.fromString("e31a2114-d247-11ed-afa1-0242ac120002")))
        .thenReturn(Optional.of(policy));

    invoiceServiceImpl.createInvoice(invoiceDto);

    Mockito.verify(policyRepository).findById(UUID.fromString("e31a2114-d247-11ed-afa1-0242ac120002"));

    Set<Policy> policies = new HashSet<>();
    policies.add(policy);

    var entityInvoice = Invoice.builder()
        .id(null)
        .cost(100f)
        .customerId("customerId")
        .status(Status.PAID.name())
        .createdDate(LocalDate.now())
        .policies(policies)
        .build();

    Mockito.verify(invoiceRepository).save(entityInvoice);

  }
}