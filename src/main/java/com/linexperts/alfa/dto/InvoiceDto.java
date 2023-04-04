package com.linexperts.alfa.dto;

import com.sun.istack.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class InvoiceDto {

  @NotNull
  private String customerId;

  @NotNull
  private Status status;

  private List<UUID> policiesIdsList;

}
