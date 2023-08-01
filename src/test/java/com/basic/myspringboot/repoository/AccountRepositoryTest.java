package com.basic.myspringboot.repoository;

import com.basic.myspringboot.entity.Account;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    void account_notfound() {
        Account acccount =
                accountRepository.findByUsername("test10") //Optional<null>
                .orElseThrow(() -> new RuntimeException("User Not Found!"));
        assertNull(acccount);
    }

    @Test @Disabled
    void account_crud() {
        Account account = new Account();
        account.setUsername("test3");
        account.setPassword("1234");

        Account savedAccount = accountRepository.save(account);
        assertEquals("test2",savedAccount.getUsername());

        Optional<Account> optional =
                accountRepository.findByUsername(savedAccount.getUsername());
        if(optional.isPresent()) {
            Account existAccount = optional.get();
            assertEquals("test2",existAccount.getUsername());
        }

    }
}