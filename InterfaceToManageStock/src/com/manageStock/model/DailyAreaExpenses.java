package com.manageStock.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyAreaExpenses {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String areaName;
	private Date date;
	private Integer trans_exp_daily;
	private Integer man_pwr_daily;
	private Integer othr_exp;
	private  String name_othr_exp;
	
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getTrans_exp_daily() {
		return trans_exp_daily;
	}
	public void setTrans_exp_daily(Integer trans_exp_daily) {
		this.trans_exp_daily = trans_exp_daily;
	}
	public Integer getMan_pwr_daily() {
		return man_pwr_daily;
	}
	public void setMan_pwr_daily(Integer man_pwr_daily) {
		this.man_pwr_daily = man_pwr_daily;
	}
	public Integer getOthr_exp() {
		return othr_exp;
	}
	public void setOthr_exp(Integer othr_exp) {
		this.othr_exp = othr_exp;
	}
	
	public String getName_othr_exp() {
		return name_othr_exp;
	}
	public void setName_othr_exp(String name_othr_exp) {
		this.name_othr_exp = name_othr_exp;
	}
	public DailyAreaExpenses() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DailyAreaExpenses(Long id, String areaName, Date date, Integer trans_exp_daily, Integer man_pwr_daily,
			Integer othr_exp, String name_othr_exp) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.date = date;
		this.trans_exp_daily = trans_exp_daily;
		this.man_pwr_daily = man_pwr_daily;
		this.othr_exp = othr_exp;
		this.name_othr_exp = name_othr_exp;
	}
	

}
