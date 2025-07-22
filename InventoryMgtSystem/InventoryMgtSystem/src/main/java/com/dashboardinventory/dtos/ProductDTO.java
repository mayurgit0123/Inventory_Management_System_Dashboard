package com.dashboardinventory.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
	
	 	private Long id;
	    private Long categoryId;
	    private Long productId;
	    private Long supplierId;
	    private String name;
	    private String sku;
	    private BigDecimal price;
	    private Integer stockQuantity;
	    private String description;
	    private LocalDateTime expiryDate;
	    private String imageUrl;
	    private LocalDateTime createdAt;

	    //getter and setter
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public Long getSupplierId() {
			return supplierId;
		}

		public void setSupplierId(Long supplierId) {
			this.supplierId = supplierId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public Integer getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(Integer stockQuantity) {
			this.stockQuantity = stockQuantity;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public LocalDateTime getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(LocalDateTime expiryDate) {
			this.expiryDate = expiryDate;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		//arg
		public ProductDTO() {};
		public ProductDTO(Long id, Long categoryId, Long productId, Long supplierId, String name, String sku,
				BigDecimal price, Integer stockQuantity, String description, LocalDateTime expiryDate, String imageUrl,
				LocalDateTime createdAt) {
			this.id = id;
			this.categoryId = categoryId;
			this.productId = productId;
			this.supplierId = supplierId;
			this.name = name;
			this.sku = sku;
			this.price = price;
			this.stockQuantity = stockQuantity;
			this.description = description;
			this.expiryDate = expiryDate;
			this.imageUrl = imageUrl;
			this.createdAt = createdAt;
		}
	    

}
