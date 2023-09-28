package com.marisia.medicare.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.marisia.medicare.model.Drug;


public interface DrugRepository extends CrudRepository<Drug, Long> {

  Iterable<Drug> findByEnabled(boolean enabled);

  Optional<Drug> findByName(String name);
  
}
