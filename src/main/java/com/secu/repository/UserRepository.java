package com.secu.repository;

import com.secu.domain.Account;
import com.secu.domain.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
