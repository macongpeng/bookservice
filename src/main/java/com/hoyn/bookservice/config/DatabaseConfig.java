package com.hoyn.bookservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.hoyn.bookservice.repository")
@EnableTransactionManagement
public class DatabaseConfig {
}
