package com.dashboardinventory.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.dashboardinventory.dtos.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAccessDenialHandler implements AccessDeniedHandler {
	
	private final ObjectMapper objectMapper;

	public CustomAccessDenialHandler(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}
	



	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.access.AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		 Response errorResponse = Response.builder()
	                .status(HttpStatus.FORBIDDEN.value())
	                .message(accessDeniedException.getMessage())
	                .build();
		 
		 response.setContentType("application/json");
	     response.setStatus(HttpStatus.FORBIDDEN.value());
	     response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
	}
	

}
