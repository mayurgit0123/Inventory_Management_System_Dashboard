package com.dashboardinventory.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRequest {
	
	@Positive(message = "product id is required")
    private Long productId;

    @Positive(message = "quantity id is required")
    private Integer quantity;

    private Long supplierId;

    private String description;

    private String note;
    
    ///////
    //getter and setter
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
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

	//arg con
	public TransactionRequest() {};
	public TransactionRequest(Long productId, Integer quantity, Long supplierId, String description, String note) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.supplierId = supplierId;
		this.description = description;
		this.note = note;
	}
    
	

}
