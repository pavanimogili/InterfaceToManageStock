package com.manageStock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categories {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
	
	private String categoryName;
	
	private Integer categoryCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Integer categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Categories(Long id, String categoryName, Integer categoryCode) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryCode = categoryCode;
	}

	public Categories() {
		
	}

}
