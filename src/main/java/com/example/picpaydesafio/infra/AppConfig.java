package com.example.picpaydesafio.infra;

import com.example.picpaydesafio.services.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public TransactionService transactionService() {
        return new TransactionService();
    }
}
