package com.linexperts.alfa.service;

import com.linexperts.alfa.dto.InvoiceInputDto;
import com.linexperts.alfa.dto.InvoiceOutputDto;
import java.util.List;
import java.util.UUID;

public interface InvoiceService {

  void createInvoice(InvoiceInputDto invoiceDto);

  InvoiceOutputDto getInvoice(UUID invoiceId);

  List<InvoiceOutputDto> getAllInvoices();

}
