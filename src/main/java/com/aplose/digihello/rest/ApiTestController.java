package com.aplose.digihello.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ApiTestController {
	
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/ping")
	public String test() {
	    try {
	        return "Welcome Admin";
	    } catch (Exception e){
	        throw new RuntimeException(e);
	    }
	} 	

}
