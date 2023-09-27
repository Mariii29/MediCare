package com.marisia.medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marisia.medicare.model.AppUser;
import com.marisia.medicare.model.SecurityUser;
import com.marisia.medicare.repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {
  @Autowired
  private AppUserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
        .findByUsername(username)
        .map(SecurityUser::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }

  public boolean usernameAlreadyExists(String username) {
    return userRepository.findByUsername(username).isPresent();
  }

  public AppUser registerUser(AppUser user) {
    return userRepository.save(user);
  }

}
