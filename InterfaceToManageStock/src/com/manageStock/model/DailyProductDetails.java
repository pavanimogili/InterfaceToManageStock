package com.manageStock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;

@Entity
public class DailyProductDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productName;
	
	private Integer quantity;
	private Integer purchasePrice;
	
	private Integer sellingprice;
	
	private Date date;
	
	public DailyProductDetails(){
		
	}


	public DailyProductDetails(Long id, String productName, Integer quantity, Integer purchasePrice,
			Integer sellingprice, Date date) {
		super();
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.sellingprice = sellingprice;
		this.date = date;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(Integer sellingprice) {
		this.sellingprice = sellingprice;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	
}
