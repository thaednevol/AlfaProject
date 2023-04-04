package com.linexperts.alfa.controller;

import com.linexperts.alfa.dto.InvoiceInputDto;
import com.linexperts.alfa.dto.MessageDto;
import com.linexperts.alfa.service.InvoiceService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class InvoiceController {
  private final InvoiceService invoiceService;
  public InvoiceController(InvoiceService invoiceService){
    this.invoiceService = invoiceService;
  }
  @PostMapping(
      path = "/invoice",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MessageDto> createInvoice(
      @RequestBody InvoiceInputDto invoiceDto) {
    var mensaje = new MessageDto();
    invoiceService.createInvoice(invoiceDto);
    return new ResponseEntity<>(mensaje, HttpStatus.OK);
  }

  @GetMapping(
      path = "/invoice/{invoiceId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MessageDto> getInvoice(@PathVariable UUID invoiceId){
    var mensaje = new MessageDto();
    mensaje.setResponse(invoiceService.getInvoice(invoiceId));
    return new ResponseEntity<>(mensaje, HttpStatus.OK);
  }

  @GetMapping(
      path = "/invoices",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MessageDto> getAllInvoices(){
    var mensaje = new MessageDto();
    mensaje.setResponse(invoiceService.getAllInvoices());
    return new ResponseEntity<>(mensaje, HttpStatus.OK);
  }

}
