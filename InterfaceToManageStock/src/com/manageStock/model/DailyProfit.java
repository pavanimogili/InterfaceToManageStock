package com.manageStock.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyProfit {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String areaName;
	private Date date;
	private Integer revenue;
	private Integer return_revenue;
	private Integer gross_margin;
	private Integer total_expense;
	private Integer dailyProfit;
	private String profit_or_loss;
	private Integer cost_of_goods_sold;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Integer getRevenue() {
		return revenue;
	}
	public void setRevenue(Integer revenue) {
		this.revenue = revenue;
	}
	public Integer getReturn_revenue() {
		return return_revenue;
	}
	public void setReturn_revenue(Integer return_revenue) {
		this.return_revenue = return_revenue;
	}
	public Integer getGross_margin() {
		return gross_margin;
	}
	public void setGross_margin(Integer gross_margin) {
		this.gross_margin = gross_margin;
	}
	public Integer getTotal_expense() {
		return total_expense;
	}
	public void setTotal_expense(Integer total_expense) {
		this.total_expense = total_expense;
	}
	public Integer getDailyProfit() {
		return dailyProfit;
	}
	public void setDailyProfit(Integer dailyProfit) {
		this.dailyProfit = dailyProfit;
	}
	public String getProfit_or_loss() {
		return profit_or_loss;
	}
	public void setProfit_or_loss(String profit_or_loss) {
		this.profit_or_loss = profit_or_loss;
	}
	
	public DailyProfit(Long id, String areaName, Date date, Integer revenue, Integer return_revenue,
			Integer gross_margin, Integer total_expense, Integer dailyProfit, String profit_or_loss,
			Integer cost_of_goods_sold) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.date = date;
		this.revenue = revenue;
		this.return_revenue = return_revenue;
		this.gross_margin = gross_margin;
		this.total_expense = total_expense;
		this.dailyProfit = dailyProfit;
		this.profit_or_loss = profit_or_loss;
		this.cost_of_goods_sold = cost_of_goods_sold;
	}
	public Integer getCost_of_goods_sold() {
		return cost_of_goods_sold;
	}
	public void setCost_of_goods_sold(Integer cost_of_goods_sold) {
		this.cost_of_goods_sold = cost_of_goods_sold;
	}
	public DailyProfit() {
		
	}

}
