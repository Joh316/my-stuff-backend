package com.example.mystuff.backend;

import com.example.mystuff.backend.bootstrap.DatabaseBootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevConfiguration {

    @Bean
    public DatabaseBootstrap databaseBootstrap (){
        return new DatabaseBootstrap();
    }
}
