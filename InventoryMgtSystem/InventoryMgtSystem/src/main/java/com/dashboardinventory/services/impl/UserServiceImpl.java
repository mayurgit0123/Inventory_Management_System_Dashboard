package com.dashboardinventory.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dashboardinventory.dtos.LoginRequest;
import com.dashboardinventory.dtos.RegisterRequest;
import com.dashboardinventory.dtos.Response;
import com.dashboardinventory.dtos.UserDTO;
import com.dashboardinventory.enums.UserRole;
import com.dashboardinventory.exceptions.InvalidCredentialsException;
import com.dashboardinventory.exceptions.NotFoundException;
import com.dashboardinventory.models.User;
import com.dashboardinventory.repositories.UserRepository;
import com.dashboardinventory.security.JwtUtils;
import com.dashboardinventory.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
	
	
	 	private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final ModelMapper modelMapper;
	    private final JwtUtils jwtUtils;
	    
	    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	    
		public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper,
				JwtUtils jwtUtils) {
			super();
			this.userRepository = userRepository;
			this.passwordEncoder = passwordEncoder;
			this.modelMapper = modelMapper;
			this.jwtUtils = jwtUtils;
		}

		@Override
		public Response registerUser(RegisterRequest registerRequest) {
			// TODO Auto-generated method stub
			UserRole role = UserRole.MANAGER;

	        if (registerRequest.getRole() != null) {
	            role = registerRequest.getRole();
	        }

	        User userToSave = User.builder()
	                .name(registerRequest.getName())
	                .email(registerRequest.getEmail())
	                .password(passwordEncoder.encode(registerRequest.getPassword()))
	                .phoneNumber(registerRequest.getPhoneNumber())
	                .role(role)
	                .build();

	        userRepository.save(userToSave);

	        return Response.builder()
	                .status(200)
	                .message("User was successfully registered")
	                .build();
		}

		@Override
		public Response loginUser(LoginRequest loginRequest) {
			// TODO Auto-generated method stub
			User user = userRepository.findByEmail(loginRequest.getEmail())
	                .orElseThrow(() -> new NotFoundException("Email Not Found"));

	        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
	            throw new InvalidCredentialsException("Password Does Not Match");
	        }
	        String token = jwtUtils.generateToken(user.getEmail());

	        return Response.builder()
	                .status(200)
	                .message("User Logged in Successfully")
	                .role(user.getRole())
	                .token(token)
	                .expirationTime("6 months")
	                .build();
		}

		@Override
		public Response getAllUsers() {
			// TODO Auto-generated method stub
			 List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

		        users.forEach(user -> user.setTransactions(null));

		        List<UserDTO> userDTOS = modelMapper.map(users, new TypeToken<List<UserDTO>>() {
		        }.getType());

		        return Response.builder()
		                .status(200)
		                .message("success")
		                .users(userDTOS)
		                .build();
		}

		@Override
		public User getCurrentLoggedInUser() {
			// TODO Auto-generated method stub
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        String email = authentication.getName();

	        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User Not Found"));

	        user.setTransactions(null);

	        return user;
		}

		@Override
		public Response getUserById(Long id) {
			// TODO Auto-generated method stub
			User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));

	        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

	        userDTO.setTransactions(null);

	        return Response.builder()
	                .status(200)
	                .message("success")
	                .user(userDTO)
	                .build();
		}

		@Override
		public Response updateUser(Long id, UserDTO userDTO) {
			// TODO Auto-generated method stub
			User existingUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));

	        if (userDTO.getEmail() != null) existingUser.setEmail(userDTO.getEmail());
	        if (userDTO.getPhoneNumber() != null) existingUser.setPhoneNumber(userDTO.getPhoneNumber());
	        if (userDTO.getName() != null) existingUser.setName(userDTO.getName());
	        if (userDTO.getRole() != null) existingUser.setRole(userDTO.getRole());

	        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
	            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	        }
	        userRepository.save(existingUser);

	        return Response.builder()
	                .status(200)
	                .message("User successfully updated")
	                .build();
		}

		@Override
		public Response deleteUser(Long id) {
			// TODO Auto-generated method stub
			 userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));

		        userRepository.deleteById(id);

		        return Response.builder()
		                .status(200)
		                .message("User successfully Deleted")
		                .build();
		}

		@Override
		public Response getUserTransactions(Long id) {
			// TODO Auto-generated method stub
			User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));

	        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

	        userDTO.getTransactions().forEach(transactionDTO -> {
	            transactionDTO.setUser(null);
	            transactionDTO.setSupplier(null);
	        });

	        return Response.builder()
	                .status(200)
	                .message("success")
	                .user(userDTO)
	                .build();
		}
		
		
	   

}
