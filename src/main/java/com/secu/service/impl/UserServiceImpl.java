package com.secu.service.impl;

import com.secu.domain.Account;
import com.secu.repository.UserRepository;
import com.secu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
