package com.dashboardinventory.dtos;

import com.dashboardinventory.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	 @NotBlank(message = "Name is required")
	    private String name;

	    @NotBlank(message = "email is required")
	    private String email;

	    @NotBlank(message = "Password is required")
	    private String password;

	    @NotBlank(message = "phoneNumber is required")
	    private String phoneNumber;

	    private UserRole role;

	    //getter and setter
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

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

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public UserRole getRole() {
			return role;
		}

		public void setRole(UserRole role) {
			this.role = role;
		}
		
//arg cons
		public RegisterRequest() {};
		public RegisterRequest(String name, String email, String password, String phoneNumber, UserRole role) {
			this.name = name;
			this.email = email;
			this.password = password;
			this.phoneNumber = phoneNumber;
			this.role = role;
		}
	    
	    

}
