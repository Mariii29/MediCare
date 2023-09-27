package com.marisia.medicare.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.marisia.medicare.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
  Optional<AppUser> findByUsername(String username);
}
