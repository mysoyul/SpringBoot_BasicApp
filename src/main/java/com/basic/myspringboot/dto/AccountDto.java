package com.basic.myspringboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Builder @ToString
public class AccountDto {
    private String accountId;

    private double balance;

    private AccountType type;
}
