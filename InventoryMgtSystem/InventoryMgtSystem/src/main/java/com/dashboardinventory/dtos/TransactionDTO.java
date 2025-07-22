package com.dashboardinventory.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.dashboardinventory.enums.TransactionStatus;
import com.dashboardinventory.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {
	
	private Long id;
    private Integer totalProducts;

    private BigDecimal totalPrice;

    private TransactionType transactionType; // pruchase, sale, return

    private TransactionStatus status; //pending, completed, processing

    private String description;
    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    private ProductDTO product;

    private UserDTO user;

    private SupplierDTO supplier;

    
    //getter and setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(Integer totalProducts) {
		this.totalProducts = totalProducts;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public SupplierDTO getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierDTO supplier) {
		this.supplier = supplier;
	}
	
	//ARg
	public TransactionDTO() {};

	public TransactionDTO(Long id, Integer totalProducts, BigDecimal totalPrice, TransactionType transactionType,
			TransactionStatus status, String description, String note, LocalDateTime createdAt, LocalDateTime updateAt,
			ProductDTO product, UserDTO user, SupplierDTO supplier) {
		super();
		this.id = id;
		this.totalProducts = totalProducts;
		this.totalPrice = totalPrice;
		this.transactionType = transactionType;
		this.status = status;
		this.description = description;
		this.note = note;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.product = product;
		this.user = user;
		this.supplier = supplier;
	}
    

}
