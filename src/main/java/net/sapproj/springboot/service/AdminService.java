package net.sapproj.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.sapproj.springboot.model.User;
import net.sapproj.springboot.web.dto.RepresRegistrationDto;
import net.sapproj.springboot.web.dto.UserRegistrationDto;

public interface AdminService extends UserDetailsService{
	User save(RepresRegistrationDto registrationDto);
}
