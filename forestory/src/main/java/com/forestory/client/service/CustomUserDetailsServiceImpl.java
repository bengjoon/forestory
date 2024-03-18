package com.forestory.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.forestory.domain.User;
import com.forestory.dto.CustomUserDetails;
import com.forestory.repository.UserRepository;

import lombok.Setter;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{
	
	@Setter(onMethod_=@Autowired)
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		
		User  user = userRepository.findByUserEmail(userEmail);
		if(user != null) {
			return new CustomUserDetails(user);
		} 
		
//		else {
//			 throw new UsernameNotFoundException(userEmail);
//		}
		
		user = User.builder().userEmail(userEmail).build();
		
		return new CustomUserDetails(user);
	}

}
