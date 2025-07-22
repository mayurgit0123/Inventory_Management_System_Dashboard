package com.dashboardinventory.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierDTO {
	
	private Long id;
	
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = " contactInfo is required")
    private String contactInfo;

    private String address;

    //getter and seeter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	//arg
	public SupplierDTO() {};
	public SupplierDTO(Long id, String name, String contactInfo, String address) {
		super();
		this.id = id;
		this.name = name;
		this.contactInfo = contactInfo;
		this.address = address;
	}
    
    

}
