package com.dashboardinventory.models;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
@Data
@Builder
public class Category {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @NotBlank(message = "Name is required")
	 private String name;
	 
	 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	 private List<Product> products;
	 
	 
	 @Override
	    public String toString() {
	        return "Category{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                '}';
	    }
	 
	 
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	//arguments
	public Category() {}
	public Category(Long id, String name, List<Product> products) {
		this.id = id;
		this.name = name;
		this.products = products;
	};
	

}
