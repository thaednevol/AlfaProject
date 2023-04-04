package com.linexperts.alfa.repository;

import com.linexperts.alfa.entity.Policy;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, UUID> {

}
