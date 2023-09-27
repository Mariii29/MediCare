package com.marisia.medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marisia.medicare.model.Medicine;
import com.marisia.medicare.repository.MedicineRepository;

@Service
public class MedicineService {

  @Autowired
  private MedicineRepository medicineRepository;

  public Iterable<Medicine> findAll() {
    return medicineRepository.findAll();
  }

  public Iterable<Medicine> findAllEnabled() {
    return medicineRepository.findByEnabled(true);
  }

  public Medicine findById(Long id) {
    return medicineRepository.findById(id).orElse(null);
  }

}
