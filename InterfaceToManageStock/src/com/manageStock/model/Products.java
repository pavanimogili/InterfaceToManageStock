package com.manageStock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private String productName;

	public Products() {
		
	}
	
	public Products(Long productId, String productName) {
		this.productId = productId;
		this.productName = productName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	
}
