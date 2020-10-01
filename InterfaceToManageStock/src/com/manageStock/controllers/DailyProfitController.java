package com.manageStock.controllers;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.manageStock.model.Area;
import com.manageStock.model.AreaWisedetails;
import com.manageStock.model.DailyAreaExpenses;
import com.manageStock.model.DailyProfit;
import com.manageStock.services.AreaService;
import com.manageStock.services.AreaWisedetailsService;
import com.manageStock.services.DailyAreaExpensesService;
import com.manageStock.services.DailyProfitService;
import com.manageStock.util.UtilityClass;

@Controller
public class DailyProfitController {
	
	@Autowired DailyProfitService service;
	
	@Autowired  UtilityClass util;
	
	@Autowired AreaWisedetailsService aService;
	
	@Autowired DailyAreaExpensesService expService;
	
	 @Autowired private AreaService areaService;
	
	@RequestMapping("/dailyProfitDashboard")
	public ModelAndView dailyProfitDashboard(HttpServletRequest request, HttpServletResponse response) {
		List <DailyProfit> dailyProfitList = null;
		List<Area> listAreas = areaService.listAll();
		String selectedArea = request.getParameter("selectedArea");
		if(null!=selectedArea) {

			dailyProfitList =service.search(selectedArea, util.getCurrentDate());
		}
		else {
			dailyProfitList =	service.search(util.getCurrentDate());
			
		}
		if(null == dailyProfitList || dailyProfitList.isEmpty()) {
			
			dailyProfitList = generateDashBoard();
		}
	 
		ModelAndView mav = new ModelAndView("generateDashBoard");
		mav.addObject("dailyProfitList", dailyProfitList);
		mav.addObject("listAreas", listAreas);
		mav.addObject("selectedArea", selectedArea);
		return mav;
		
	}
	
	
	public  List<DailyProfit> generateDashBoard() {

		Date date = util.getCurrentDate();
		List<DailyAreaExpenses> expensesList = expService.search(date);
		List<AreaWisedetails> CostList = aService.searchByDate(date);
		
		Set<String> areaNames = CostList.stream().map(AreaWisedetails :: getAreaName).collect(Collectors.toSet());
		for(String area :areaNames) {
			DailyProfit dailyProfit = new DailyProfit();
			dailyProfit.setAreaName(area);
			dailyProfit.setDate(date);
			 List<AreaWisedetails> list = CostList.stream()
                     .filter(s -> s.areaName.equals(area))
                     .collect(Collectors.toList());
			 Integer revenue = 0;
					 Integer returnedRev = 0;
					 Integer cost_of_goods_sold =0;
							 
			 for(AreaWisedetails details: list) {
				  revenue =revenue +( details.getQuantity() * details.getSellingprice());
					 returnedRev = returnedRev +( details.getReturnedQuantity() * details.getSellingprice());
					 cost_of_goods_sold = cost_of_goods_sold+((details.getQuantity()-details.getReturnedQuantity())*details.getPurchasePrice());
			 }
			 Integer grossMargin = revenue - returnedRev;
			 dailyProfit.setRevenue(revenue);
			 dailyProfit.setReturn_revenue(returnedRev);
			 dailyProfit.setGross_margin(grossMargin);
			 dailyProfit.setCost_of_goods_sold(cost_of_goods_sold);
			 for (DailyAreaExpenses expense : expensesList) {
					if (expense.getAreaName().equals(area)) {
						Integer totalExpences = expense.getMan_pwr_daily() + expense.getTrans_exp_daily()
								+ expense.getOthr_exp();
						dailyProfit.setTotal_expense(totalExpences);
					}
				}
			 Integer Profit = dailyProfit.getGross_margin() - dailyProfit.getTotal_expense()
						- dailyProfit.getCost_of_goods_sold();

				if (Profit > 1) {
					dailyProfit.setProfit_or_loss("profit");
				} else {
					dailyProfit.setProfit_or_loss("loss");
				}
				dailyProfit.setDailyProfit(Profit);
				service.save(dailyProfit);
		}

		return service.search(date);

	}
	
	
	@RequestMapping("/deleteRecordsAndGenerateDashBoard")
	public String deleteCategoryRecord(@RequestParam long id) {
		
		List <DailyProfit> 	dailyProfitList =	service.search(util.getCurrentDate());
		for(DailyProfit profit:dailyProfitList) {
		service.delete(profit.getId());
		}
		return "redirect:/dailyProfitDashboard";
	}
	  
	
}
