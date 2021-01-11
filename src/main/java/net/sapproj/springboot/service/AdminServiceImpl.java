package net.sapproj.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.sapproj.springboot.model.Role;
import net.sapproj.springboot.model.User;
import net.sapproj.springboot.repository.UserRepository;
import net.sapproj.springboot.web.dto.RepresRegistrationDto;
import net.sapproj.springboot.web.dto.UserRegistrationDto;


@Service
public class AdminServiceImpl implements AdminService{
	
    private UserRepository userRepository;

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public AdminServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	

	@Override
	public User save(RepresRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_REPRES")));
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User admin = userRepository.findByEmail(username);
		if(admin == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), mapRolesToAuthorities(admin.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
