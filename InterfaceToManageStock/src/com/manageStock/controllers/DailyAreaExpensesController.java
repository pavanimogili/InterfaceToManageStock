package com.manageStock.controllers;

import java.util.ArrayList;
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
import com.manageStock.model.DailyAreaExpenses;
import com.manageStock.model.MonthlyExpenses;
import com.manageStock.services.AreaService;
import com.manageStock.services.DailyAreaExpensesService;
import com.manageStock.services.MonthlyExpensesService;
import com.manageStock.util.UtilityClass;

@Controller
public class DailyAreaExpensesController {
	
	  
	  
	  @Autowired DailyAreaExpensesService service;
	  
	  @Autowired private UtilityClass util;
	  
	  @Autowired private AreaService areaService;
	  
	  @Autowired MonthlyExpensesService mnthlyExpnsService;
	  
		@RequestMapping("/dailyAreaExpenses")
		public ModelAndView dailyAreaExpensesHome(HttpServletRequest request, HttpServletResponse response) {
			
			String error = request.getParameter("error");
			List<DailyAreaExpenses> expensesList = null;
			Date date = util.getCurrentDate();
			int noOfDayInCurrentMnth = util.getNoOfDayInCurrentMnth();
			List<MonthlyExpenses> monthlyExpensesList = mnthlyExpnsService.search(util.getFirstDayOfMonth());
			List<Area> listAreas = areaService.listAll();
			String selectedArea = request.getParameter("selectedArea");

			if (null != selectedArea) {
				expensesList = service.search(selectedArea, date);
			} else {
				expensesList = service.search(date);
			}

			List<DailyAreaExpenses> expensesList2 = new ArrayList<DailyAreaExpenses>();
			for (MonthlyExpenses mExpns : monthlyExpensesList) {
				DailyAreaExpenses expens = new DailyAreaExpenses();
				expens.setAreaName(mExpns.getAreaName());
				expens.setDate(date);
				Integer man_pwr_daily = Math.round(mExpns.getMan_pwr_mnthly() / noOfDayInCurrentMnth);
				Integer trans_exp_daily = Math.round(mExpns.getTrans_exp_mnthly() / noOfDayInCurrentMnth);

				expens.setMan_pwr_daily(man_pwr_daily);
				expens.setTrans_exp_daily(trans_exp_daily);
				expensesList2.add(expens);
			}

			ModelAndView mav = new ModelAndView("dailyAreaExpenses");
			if (null == expensesList || expensesList.isEmpty()) {
				mav.addObject("expensesList", expensesList2);
			} else {
				mav.addObject("expensesList", expensesList);
			}
			mav.addObject("expensesList2", expensesList2);
			mav.addObject("listAreas", listAreas);
			mav.addObject("selectedArea", selectedArea);
			if(null!= error) {
	        	mav.addObject("error", error);
	        }
			return mav;

		}
	  
		@RequestMapping(value = "/saveDailyExpensesRecord", method = RequestMethod.POST)
		public String saveDailyExpensesRecord(HttpServletRequest request, HttpServletResponse response) {
			Date date = util.getCurrentDate();
			String[] selectedItems = request.getParameterValues("selected");
			for (String selectedItem : selectedItems) {
				
				if(util.checkDuplicateExpnsArea(request.getParameter("areaName" + selectedItem))) {
					return "redirect:/dailyAreaExpenses?error='duplicate'";
				}
				DailyAreaExpenses record = new DailyAreaExpenses();
				
				if (!StringUtils.isBlank(request.getParameter("id" + selectedItem))) {
					record.setId(Long.parseLong(request.getParameter("id" + selectedItem)));
				}

				record.setAreaName(request.getParameter("areaName" + selectedItem));
				record.setDate(date);
				record.setMan_pwr_daily(Integer.parseInt(request.getParameter("man_pwr_daily" + selectedItem)));
				record.setName_othr_exp(request.getParameter("name_othr_exp" + selectedItem));
				record.setOthr_exp(Integer.parseInt(request.getParameter("othr_exp" + selectedItem)));
				record.setTrans_exp_daily(Integer.parseInt(request.getParameter("trans_exp_daily" + selectedItem)));
				service.save(record);
			}

			return "redirect:/dailyAreaExpenses";

		}

		@RequestMapping("/deleteDailyExpensesRecord")
		public String deleteExpensesRecord(@RequestParam long id) {
			service.delete(id);
			return "redirect:/dailyAreaExpenses";
		}
	 
}
