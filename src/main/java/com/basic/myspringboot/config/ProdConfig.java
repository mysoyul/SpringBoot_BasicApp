package com.basic.myspringboot.config;

import com.basic.myspringboot.dto.AccountDto;
import com.basic.myspringboot.dto.AccountType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class ProdConfig {
    @Bean
    public AccountDto account() {
        return AccountDto.builder()  //AccountDtoBuilder
                .accountId("5678-1234")
                .balance(20000.0)
                .type(AccountType.CHECKING)
                .build();
    }
}
