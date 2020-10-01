package com.manageStock.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class AreaWisedetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productName;
	
	private Integer quantity;
	private Integer purchasePrice;
	
	private Integer sellingprice;
	
	private Date date;
	
	public String areaName;
	
	private Integer receivedAmount;
	private Integer returnedQuantity;
	
	private String reasonForReturn;
	

	public AreaWisedetails(Long id, String productName, Integer quantity, Integer purchasePrice, Integer sellingprice,
			Date date, String areaName, Integer receivedAmount, Integer returnedQuantity, String reasonForReturn) {
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.sellingprice = sellingprice;
		this.date = date;
		this.areaName = areaName;
		this.receivedAmount = receivedAmount;
		this.returnedQuantity = returnedQuantity;
		this.reasonForReturn = reasonForReturn;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	
	
	public AreaWisedetails() {
		
	}

	public Integer getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(Integer receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public Integer getReturnedQuantity() {
		return returnedQuantity;
	}

	public void setReturnedQuantity(Integer returnedQuantity) {
		this.returnedQuantity = returnedQuantity;
	}

	public String getReasonForReturn() {
		return reasonForReturn;
	}

	public void setReasonForReturn(String reasonForReturn) {
		this.reasonForReturn = reasonForReturn;
	}

	
	

}
