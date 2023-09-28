package com.marisia.medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marisia.medicare.model.Drug;
import com.marisia.medicare.repository.DrugRepository;

@Service
public class DrugService {

  @Autowired
  private DrugRepository drugRepository;

  public Iterable<Drug> findAll() {
    return drugRepository.findAll();
  }

  public Iterable<Drug> findAllEnabled() {
    return drugRepository.findByEnabled(true);
  }

  public Drug findById(Long id) {
    return drugRepository.findById(id).orElse(null);
  }

  public boolean drugWithNameAlreadyExists(String name) {
    return drugRepository.findByName(name).isPresent();
  }

  public Drug addDrug(Drug drug) {
    return drugRepository.save(drug);
  }

}
