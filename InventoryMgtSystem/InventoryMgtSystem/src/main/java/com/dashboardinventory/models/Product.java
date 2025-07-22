package com.dashboardinventory.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Data
@Builder
public class Product {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @NotBlank(message = "Name is required")
	 private String name;
	 
	 @Column(unique = true)
	 @NotBlank(message = "SKU is required")
	 private String sku;
	 
	 @Positive(message = "product price must be a positive value")
	 private BigDecimal price;
	 
	 @Min(value = 0, message = "stock quantity cannot be negative")
	 private Integer stockQuantity;
	 
	 private String description;
	 private LocalDateTime expiryDate;
	 private String imageUrl;

	 private final LocalDateTime createdAt = LocalDateTime.now();
	 
	 @ManyToOne
	    @JoinColumn(name = "category_id")
	 private Category category;
	 
	 @Override
	    public String toString() {
	        return "Product{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", sku='" + sku + '\'' +
	                ", price=" + price +
	                ", stockQuantity=" + stockQuantity +
	                ", description='" + description + '\'' +
	                ", expiryDate=" + expiryDate +
	                ", imageUrl='" + imageUrl + '\'' +
	                ", createdAt=" + createdAt +
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public Product() {};
	public Product(Long id, String name, String sku, BigDecimal price, Integer stockQuantity, String description,
			LocalDateTime expiryDate, String imageUrl, Category category) {
		this.id = id;
		this.name = name;
		this.sku = sku;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.description = description;
		this.expiryDate = expiryDate;
		this.imageUrl = imageUrl;
		this.category = category;
	}
	
	
	
	public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String sku;
        private BigDecimal price;
        private Integer stockQuantity;
        private String description;
        private LocalDateTime expiryDate;
        private String imageUrl;
        private Category category;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder sku(String sku) {
            this.sku = sku;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder stockQuantity(Integer stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder expiryDate(LocalDateTime expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Product build() {
            return new Product(id, name, sku, price, stockQuantity, description, expiryDate, imageUrl, category);
        }
    }

	 
	 
}
