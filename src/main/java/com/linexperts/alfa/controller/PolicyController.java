package com.linexperts.alfa.controller;

import com.linexperts.alfa.dto.MessageDto;
import com.linexperts.alfa.service.PolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class PolicyController {
  private final PolicyService policyService;
  public PolicyController(PolicyService policyService){
    this.policyService = policyService;
  }

  @GetMapping(
      path = "/policy",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MessageDto> getPolicies(){
    var message = new MessageDto();
    message.setResponse(policyService.listPolicies());
    return new ResponseEntity<>(message, HttpStatus.OK);
  }

}
