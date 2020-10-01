package com.manageStock.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MonthlyExpenses {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String areaName;
	
	private Integer trans_exp_mnthly;
	
	private Integer man_pwr_mnthly;
	
	private Date date;


	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getTrans_exp_mnthly() {
		return trans_exp_mnthly;
	}

	public void setTrans_exp_mnthly(Integer trans_exp_mnthly) {
		this.trans_exp_mnthly = trans_exp_mnthly;
	}

	public Integer getMan_pwr_mnthly() {
		return man_pwr_mnthly;
	}

	public void setMan_pwr_mnthly(Integer man_pwr_mnthly) {
		this.man_pwr_mnthly = man_pwr_mnthly;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public MonthlyExpenses(Long id, String areaName, Integer trans_exp_mnthly, Integer man_pwr_mnthly, Date date) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.trans_exp_mnthly = trans_exp_mnthly;
		this.man_pwr_mnthly = man_pwr_mnthly;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MonthlyExpenses() {
		
	}
	

}
