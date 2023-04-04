package com.linexperts.alfa.dto;

import com.sun.istack.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceOutputDto {

  @NotNull
  private String customerId;

  @NotNull
  private Status status;

  private LocalDate dateCreated;

  private Float cost;

  private List<UUID> policiesIdsList;

}
