package com.linexperts.alfa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto<T> {
  private T response;

  public void setResponse(T response) {
    this.response = response;
  }
}
