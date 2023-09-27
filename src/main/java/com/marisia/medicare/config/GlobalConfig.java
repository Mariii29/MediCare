package com.marisia.medicare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marisia.medicare.service.storage.FileSystemStorage;
import com.marisia.medicare.service.storage.StorageProperties;
import com.marisia.medicare.service.storage.StorageService;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
public class GlobalConfig {
  @Bean
  LayoutDialect layoutDialect() {
    return new LayoutDialect();
  }

  @Bean
  StorageService storageService(StorageProperties properties) {
    return new FileSystemStorage(properties);
  }

}
