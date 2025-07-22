package com.dashboardinventory.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
@Data
@Builder
public class Supplier {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is required")
	private String name;

    @NotBlank(message = " contactInfo is required")
	private String contactInfo;
    
	private String address;
	
	
//getter and setter
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
	
	public Supplier() {};
	public Supplier(Long id, String name, String contactInfo, String address) {
		super();
		this.id = id;
		this.name = name;
		this.contactInfo = contactInfo;
		this.address = address;
	}
	

}
