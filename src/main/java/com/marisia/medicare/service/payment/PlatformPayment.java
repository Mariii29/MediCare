package com.marisia.medicare.service.payment;

import org.springframework.beans.factory.annotation.Autowired;

import com.marisia.medicare.model.Payment;
import com.marisia.medicare.repository.PaymentRepository;

public class PlatformPayment implements PaymentService {
  @Autowired
  final PaymentRepository repository;

  public PlatformPayment(PaymentRepository repository) {
    this.repository = repository;
  }

  @Override
  public Payment make(Payment payment) {
    return repository.save(payment);
  }

}
