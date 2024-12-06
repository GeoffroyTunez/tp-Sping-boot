package com.aplose.digihello.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aplose.digihello.dto.AuthRequestDTO;
import com.aplose.digihello.dto.JwtResponseDTO;
import com.aplose.digihello.service.JwtService;

@RestController
@CrossOrigin
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/api/v1/login")
	public JwtResponseDTO authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
	    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
	    if(authentication.isAuthenticated()){
	    	JwtResponseDTO jwtResponseDTO=new JwtResponseDTO();
	    	jwtResponseDTO.setAccessToken(jwtService.GenerateToken(authRequestDTO.getUsername()));
	       return jwtResponseDTO;
	    } else {
	        throw new UsernameNotFoundException("invalid user request..!!");
	    }
	}
}
