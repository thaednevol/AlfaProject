package com.linexperts.alfa.dto;

import com.sun.istack.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceInputDto {

  @NotNull
  private String customerId;

  @NotNull
  private Status status;

  private List<UUID> policiesIdsList;

}
