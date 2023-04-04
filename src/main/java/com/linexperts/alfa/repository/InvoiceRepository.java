package com.linexperts.alfa.repository;

import com.linexperts.alfa.entity.Invoice;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

}
