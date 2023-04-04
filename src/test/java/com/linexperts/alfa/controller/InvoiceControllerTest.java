package com.linexperts.alfa.controller;


import com.linexperts.alfa.dto.InvoiceInputDto;
import com.linexperts.alfa.dto.MessageDto;
import com.linexperts.alfa.dto.Status;
import com.linexperts.alfa.service.InvoiceService;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.message.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InvoiceControllerTest {

  @Mock
  private InvoiceService invoiceService;

  @InjectMocks
  InvoiceController invoiceController;

  @Test
  void createInvoice() {
    var invoiceDto = InvoiceInputDto.builder().customerId("customerId").policiesIdsList(List.of(
        UUID.fromString("e31a2114-d247-11ed-afa1-0242ac120002"))).status(Status.PAID).build();

    Mockito.doNothing().when(invoiceService).createInvoice(invoiceDto);

    ResponseEntity<MessageDto> response = invoiceController.createInvoice(invoiceDto);

    Assertions.assertEquals(200, response.getStatusCodeValue());

  }

  @Test
  void getInvoice() {
  }

  @Test
  void getAllInvoices() {
  }
}