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

  public boolean drugWithNameAlreadyExists(String name, Long updateId) {
    var drug = drugRepository
        .findByName(name).orElse(new Drug());

    return !drug.getId().equals(updateId);
  }

  public Drug addDrug(Drug drug) {
    return drugRepository.save(drug);
  }

  public Drug update(Drug drug) {
    var mDrug = drugRepository.findById(drug.getId()).get();
    mDrug.setId(drug.getId());
    mDrug.setName(drug.getName());
    mDrug.setDescription(drug.getDescription());
    mDrug.setEnabled(drug.getEnabled());
    mDrug.setPrice(drug.getPrice());
    mDrug.setSeller(drug.getSeller());

    return drugRepository.save(mDrug);
  }

  public void delete(Drug drug) {
    drugRepository.delete(drug);
  }

}
