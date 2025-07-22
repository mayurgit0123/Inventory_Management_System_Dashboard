package com.dashboardinventory.services;

import com.dashboardinventory.dtos.LoginRequest;
import com.dashboardinventory.dtos.RegisterRequest;
import com.dashboardinventory.dtos.Response;
import com.dashboardinventory.dtos.UserDTO;
import com.dashboardinventory.models.User;

public interface UserService {
	
	Response registerUser(RegisterRequest registerRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getCurrentLoggedInUser();

    Response getUserById(Long id);

    Response updateUser(Long id, UserDTO userDTO);

    Response deleteUser(Long id);

    Response getUserTransactions(Long id);

}
