package org.felipe.springbootapp;

import org.felipe.springbootapp.features.account.AccountRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringbootappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootappApplication.class, args);
    }


    @Bean
    AccountRepository accountRepository(DataSource datasource) {
        return new AccountRepository(datasource);
    }


}
