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

import com.manageStock.model.DailyProductDetails;
import com.manageStock.model.Products;
import com.manageStock.services.DailyproductdetailsService;
import com.manageStock.services.ProductService;
import com.manageStock.util.UtilityClass;

@Controller
public class ManageDailyProductsController {
	
	 @Autowired
		private DailyproductdetailsService service;
	 @Autowired
		private ProductService productService;
	 
	 @Autowired
	 private UtilityClass util;
	 
	 @RequestMapping("/dailyProdutUpdates")
	    public ModelAndView dailyProdutUpdatesHome(HttpServletRequest request, HttpServletResponse response) {
		 String error = request.getParameter("error");
		 
		 List<DailyProductDetails> dailyproductList = service.search(util.getCurrentDate());
		 List<Products> listProducts = productService.listAll();
		 if(dailyproductList.isEmpty()) {
			 for(Products product:listProducts) {
				 DailyProductDetails details = new DailyProductDetails();
				 details.setProductName(product.getProductName());
				 dailyproductList.add(details);
			 }
			 
		 }
		 
			   ModelAndView mav = new ModelAndView("dailyProdutUpdates");
			   mav.addObject("listDetails", dailyproductList); 
			   mav.addObject("products", listProducts); 
			   
			   if(null!= error) {
		        	mav.addObject("error", error);
		        }
			   return mav;
			 
	    }
	 
	 @RequestMapping(value = "/saveProductRecord", method = RequestMethod.POST)
	    public String saveProductRecord(HttpServletRequest request, HttpServletResponse response) { 
		 
		 Date date = util.getCurrentDate();
		 
	    	String[] selectedItems = request.getParameterValues("selected");
	    	
	    	for (String selectedItem : selectedItems) {
	    		
	    		 if(util.checkDuplicateDailyPrdct(request.getParameter("productName" + selectedItem))) {
	 				return "redirect:/dailyProdutUpdates?error='duplicate'";
	 			}
	    		
	    		DailyProductDetails record = new DailyProductDetails();
	    		if(!StringUtils.isBlank(request.getParameter("id"+selectedItem))) {
	    			record.setId(Long.parseLong(request.getParameter("id"+selectedItem)));
	    		}
	    		
	    		record.setProductName(request.getParameter("productName"+selectedItem));
	    		record.setPurchasePrice(Integer.parseInt(request.getParameter("purchasePrice"+selectedItem)));
	    		record.setQuantity(Integer.parseInt(request.getParameter("quantity"+selectedItem)));
	    		record.setSellingprice(Integer.parseInt(request.getParameter("sellingprice"+selectedItem)));
	    		record.setDate(date);
	    		
	    		service.save(record);
	    	}
	    	
	        return "redirect:/dailyProdutUpdates";
	    }
	 
	 
	 @RequestMapping("/deleteProductRecord")
	    public String deleteProduct(@RequestParam long id) {
		 service.delete(id);
	        return "redirect:/dailyProdutUpdates";       
	    }
	 
	   @RequestMapping("/searchByProduct")
	    public ModelAndView searchByProduct(@RequestParam String keyword) {
		   List<DailyProductDetails> dailyproductList =  service.search(keyword,util.getCurrentDate());
		   List<Products> listProducts = productService.listAll();
			 if(dailyproductList.isEmpty()) {
				 for(Products product:listProducts) {
					 DailyProductDetails details = new DailyProductDetails();
					 details.setProductName(product.getProductName());
					 dailyproductList.add(details);
				 }
				 
			 }
			 
				   ModelAndView mav = new ModelAndView("dailyProdutUpdates");
				   mav.addObject("listDetails", dailyproductList); 
				   mav.addObject("products", listProducts); 
				   return mav;
	     
	    }
	   
	   
	    
}
