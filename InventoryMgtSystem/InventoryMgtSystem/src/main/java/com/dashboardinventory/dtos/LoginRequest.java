package com.dashboardinventory.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	
	@NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    //getter and setter
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginRequest() {};
	public LoginRequest(String email,
			String password) {
		this.email = email;
		this.password = password;
	}
    
    

}
