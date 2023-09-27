package com.marisia.medicare.repository;

import org.springframework.data.repository.CrudRepository;

import com.marisia.medicare.model.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Long> {

  Iterable<Medicine> findByEnabled(boolean enabled);
  
}
