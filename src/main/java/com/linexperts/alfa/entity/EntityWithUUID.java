package com.linexperts.alfa.entity;

import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public class EntityWithUUID {
  @Id
  final private UUID id;

  public EntityWithUUID() {
    this.id = UUID.randomUUID();
  }

}
