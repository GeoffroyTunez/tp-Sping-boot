package com.aplose.digihello.repository;

import com.aplose.digihello.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    public UserAccount findByUsername(String username);
}
