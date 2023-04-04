package com.linexperts.alfa.service;

import com.linexperts.alfa.entity.Policy;
import com.linexperts.alfa.repository.PolicyRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PolicyServiceImpl implements PolicyService {

  private final PolicyRepository policyRepository;
  @Override
  public List<Policy> listPolicies() {
    return policyRepository.findAll();
  }
}
