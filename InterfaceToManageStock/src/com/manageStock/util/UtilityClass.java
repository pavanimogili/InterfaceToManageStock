package com.manageStock.util;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manageStock.model.Area;
import com.manageStock.model.AreaWisedetails;
import com.manageStock.model.Categories;
import com.manageStock.model.DailyAreaExpenses;
import com.manageStock.model.DailyProductDetails;
import com.manageStock.model.MonthlyExpenses;
import com.manageStock.model.Products;
import com.manageStock.services.AreaService;
import com.manageStock.services.AreaWisedetailsService;
import com.manageStock.services.CategoriesService;
import com.manageStock.services.DailyAreaExpensesService;
import com.manageStock.services.DailyproductdetailsService;
import com.manageStock.services.MonthlyExpensesService;
import com.manageStock.services.ProductService;

@Component
public class UtilityClass {
	
	 @Autowired
		private AreaService areaService;
	 
	 @Autowired
		private ProductService productService;
	 
	  @Autowired DailyAreaExpensesService areaDailyexpnsservice;
	  
	  @Autowired MonthlyExpensesService mnthlyExpnsService;
	  
	  @Autowired
		private DailyproductdetailsService dailyPrdctService;
	  
		@Autowired AreaWisedetailsService areaWiseDailyService;
		
		@Autowired CategoriesService catService;
	
	public Date getCurrentDate() {
		
		LocalDate localDate = LocalDate.now();
		 Date date = java.sql.Date.valueOf(localDate);
		return date;
		
	}

	
public Date getFirstDayOfMonth() {
		
		LocalDate localDate = LocalDate.now();
		 Date date = java.sql.Date.valueOf(localDate.withDayOfMonth(1));
		return date;
		
	}

public int getNoOfDayInCurrentMnth() {
	Calendar c = Calendar.getInstance();
	int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	return monthMaxDays;
}

public boolean checkDuplicateArea(String areaName) {
	
	 List<Area> listArea = areaService.listAll();
	 Set<String> areaNames = listArea.stream().map(Area :: getAreaName).collect(Collectors.toSet());
	 for(String name : areaNames) {
 		if(name.equalsIgnoreCase(areaName)) {
 			return true;
 		}
 		}
	
	return false;
	
}
public boolean checkDuplicateProduct(String productName) {
	
	 List<Products> listProducts = productService.listAll();
	 Set<String> productNames = listProducts.stream().map(Products :: getProductName).collect(Collectors.toSet());
	 for(String name : productNames) {
		if(name.equalsIgnoreCase(productName)) {
			return true;
		}
		}
	
	return false;
	
}


public boolean checkDuplicateExpnsArea(String areaName) {
	
	List<DailyAreaExpenses> List  = areaDailyexpnsservice.search(getCurrentDate());
	 Set<String> areaNames = List.stream().map(DailyAreaExpenses :: getAreaName).collect(Collectors.toSet());
	 for(String name : areaNames) {
		if(name.equalsIgnoreCase(areaName)) {
			return true;
		}
		}
	
	return false;
	
}


public boolean checkDuplicateMnthlyExpns(String areaName) {
	List<MonthlyExpenses> List  = mnthlyExpnsService.search(getFirstDayOfMonth());
	 Set<String> areaNames = List.stream().map(MonthlyExpenses :: getAreaName).collect(Collectors.toSet());
	 for(String name : areaNames) {
		if(name.equalsIgnoreCase(areaName)) {
			return true;
		}
		}
	
	return false;
}


public boolean checkDuplicateDailyPrdct(String parameter) {
	List<DailyProductDetails> List  = dailyPrdctService.search(getCurrentDate());
	 Set<String> Names = List.stream().map(DailyProductDetails :: getProductName).collect(Collectors.toSet());
	 for(String name : Names) {
		if(name.equalsIgnoreCase(parameter)) {
			return true;
		}
		}
	
	return false;
}


public boolean checkDuplicateAreaDailyPrdct(String selectedArea, String pName) {
	List<AreaWisedetails> areaWisedetailsList = areaWiseDailyService.searchByAreaNameAndDateAndProductName(selectedArea, getCurrentDate(), pName);
	if(!areaWisedetailsList.isEmpty()) {
		return true;
		
	}
	return false;
}


public boolean checkDuplicateCategor(String name, Integer code) {
	List<Categories> listCategories = catService.findByCategory_name(name);
	if(listCategories.isEmpty()) {
		listCategories = catService.findByCategory_code(code);
		if(listCategories.isEmpty()) {
			return false;
		}
	}
	return true;
}

}
