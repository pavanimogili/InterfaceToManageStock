package com.manageStock.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.manageStock.model.Area;
import com.manageStock.model.MonthlyExpenses;
import com.manageStock.services.AreaService;
import com.manageStock.services.MonthlyExpensesService;
import com.manageStock.util.UtilityClass;

@Controller
public class MonthlyExpensesController {
	
	@Autowired MonthlyExpensesService service;
	
	@Autowired
	 private UtilityClass util;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/areaMonthlyExpense")
	public ModelAndView areaMonthlyExpensesHome(HttpServletRequest request, HttpServletResponse response) {
		String error = request.getParameter("error");
		Date date = util.getFirstDayOfMonth();
		List<MonthlyExpenses> monthlyExpensesList = null;
		String selectedArea = request.getParameter("selectedArea");
		List<Area> listAreas = areaService.listAll();

		if (StringUtils.isBlank(selectedArea)) {
			monthlyExpensesList = service.search(date);
		} else {
			monthlyExpensesList = service.search(selectedArea, date);
		}

		if (null == monthlyExpensesList || monthlyExpensesList.isEmpty()) {
			for (Area area : listAreas) {
				MonthlyExpenses expense = new MonthlyExpenses();
				expense.setAreaName(area.getAreaName());
				expense.setDate(date);
				monthlyExpensesList.add(expense);
			}

		}

		ModelAndView mav = new ModelAndView("areaMonthlyExpense");
		mav.addObject("listDetails", monthlyExpensesList);
		mav.addObject("listAreas", listAreas);
		mav.addObject("selectedArea", selectedArea);
		if(null!= error) {
        	mav.addObject("error", error);
        }
		return mav;

	}
 
	
	  @RequestMapping(value = "/saveAreaMonthlyExpense", method = RequestMethod.POST)
	  public String saveProductRecord(HttpServletRequest request,
	  HttpServletResponse response) {
	  Date date = util.getFirstDayOfMonth();
	  
	  String[] selectedItems = request.getParameterValues("selected");
	  
	  for (String selectedItem : selectedItems) {
	  
		  
		  if(util.checkDuplicateMnthlyExpns(request.getParameter("areaName" + selectedItem))) {
				return "redirect:/areaMonthlyExpense?error='duplicate'";
			}
		  MonthlyExpenses record = new MonthlyExpenses();
		  
	  if(!StringUtils.isBlank(request.getParameter("id"+selectedItem))) {
	  record.setId(Long.parseLong(request.getParameter("id"+selectedItem)));
	  }
	  record.setAreaName(request.getParameter("areaName"+selectedItem));
	  record.setDate(date);
	  record.setMan_pwr_mnthly(Integer.parseInt(request.getParameter("man_pwr_mnthly"+selectedItem)));
	  record.setTrans_exp_mnthly(Integer.parseInt(request.getParameter("trans_exp_mnthly"+selectedItem)));
	  service.save(record); 
	  }
	  
	  return "redirect:/areaMonthlyExpense"; 
	  }
	  
	  
	@RequestMapping("/deleteAreaMnthlyExpnsRecord")
	public String deleteAreaMnthlyExpnsRecord(@RequestParam long id) {
		service.delete(id);
		return "redirect:/areaMonthlyExpense";
	}
	  
   


}
