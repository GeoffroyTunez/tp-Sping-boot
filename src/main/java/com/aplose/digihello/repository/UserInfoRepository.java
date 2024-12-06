package com.aplose.digihello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aplose.digihello.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	   public UserInfo findByUsername(String username);
}
