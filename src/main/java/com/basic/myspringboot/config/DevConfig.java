package com.basic.myspringboot.config;

import com.basic.myspringboot.dto.AccountDto;
import com.basic.myspringboot.dto.AccountType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevConfig {
    @Bean
    public AccountDto account() {
        return AccountDto.builder()  //AccountDtoBuilder
                .accountId("1234-5678")
                .balance(1000.0)
                .type(AccountType.SAVING)
                .build();
    }
}
