package com.manageStock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Area {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long areaId;
	
	private String areaName;

	public Area() {
		
	}
	public Area(Long areaId, String areaName) {
		this.areaId = areaId;
		this.areaName = areaName;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	

}
