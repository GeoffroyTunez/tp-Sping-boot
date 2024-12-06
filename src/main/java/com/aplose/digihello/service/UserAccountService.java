package com.aplose.digihello.service;

import com.aplose.digihello.model.UserAccount;
import com.aplose.digihello.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        UserAccount user = new UserAccount("user",passwordEncoder.encode("password"),"ROLE_USER");
        UserAccount user2 = new UserAccount("admin",passwordEncoder.encode("password"),"ROLE_ADMIN");

        userAccountRepository.save(user);
        userAccountRepository.save(user2);
    }

}
