package com.aplose.digihello.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aplose.digihello.model.CustomUserDetails;
import com.aplose.digihello.model.UserInfo;
import com.aplose.digihello.model.UserRole;
import com.aplose.digihello.repository.UserInfoRepository;
import com.aplose.digihello.repository.UserRoleRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostConstruct
    private void init() {
    	if(userInfoRepository.count()==0) {
    	UserRole adminRole = new UserRole();
    	UserRole userRole = new UserRole();
    	adminRole.setName("ROLE_ADMIN");
    	userRole.setName("ROLE_USER");
    	UserInfo adminInfo = new UserInfo();
    	adminInfo.setUsername("admin");
    	adminInfo.setPassword(passwordEncoder.encode("admin"));
    	UserInfo userInfo = new UserInfo();
    	userInfo.setUsername("user");
    	userInfo.setPassword(passwordEncoder.encode("user"));
    	adminInfo.getRoles().add(adminRole);
    	userInfo.getRoles().add(userRole);
    	//save
    	userRoleRepository.save(adminRole);
    	userRoleRepository.save(userRole);
    	userInfoRepository.save(adminInfo);
    	userInfoRepository.save(userInfo);
    	}
    }

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.debug("Entering in loadUserByUsername Method...");
        UserInfo user = userInfoRepository.findByUsername(username);
        if(user == null){
            logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        logger.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(user);
    }
}