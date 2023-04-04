package com.linexperts.alfa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invoice implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private UUID id;
  @Column(name = "id_customer", nullable = false)
  private String customerId;

  @Column(name = "created_date", nullable = false)
  private LocalDate createdDate;

  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "cost", nullable = false)
  private Float cost;

  @ManyToMany
  @JoinTable(
      name = "policy_invoice",
      joinColumns = @JoinColumn(name = "invoice_id"),
      inverseJoinColumns = @JoinColumn(name = "policy_id"))
  Set<Policy> policies;

}
