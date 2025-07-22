package com.dashboardinventory.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dashboardinventory.exceptions.NotFoundException;
import com.dashboardinventory.models.User;
import com.dashboardinventory.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 User user = userRepository.findByEmail(username)
	                .orElseThrow(() -> new NotFoundException("User Email Not Found"));

	        return AuthUser.builder()
	                .user(user)
	                .build();
	}

}
