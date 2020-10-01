package com.manageStock.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import com.manageStock.model.AreaWisedetails;
import com.manageStock.model.DailyProductDetails;
import com.manageStock.model.Products;
import com.manageStock.services.AreaService;
import com.manageStock.services.AreaWisedetailsService;
import com.manageStock.services.DailyproductdetailsService;
import com.manageStock.services.ProductService;
import com.manageStock.util.UtilityClass;

@Controller
public class ManageAreaWisedetailsController {
	
	@Autowired AreaWisedetailsService service;
	
	 @Autowired
		private DailyproductdetailsService productDetailservice;
	
	@Autowired
	private ProductService productService;
	
	 @Autowired
		private AreaService areaService;
 
 @Autowired
 private UtilityClass util;
 
 @RequestMapping("/areaWisedetails")
    public ModelAndView dailyProdutUpdatesHome(HttpServletRequest request, HttpServletResponse response) {
	 
	 String error = request.getParameter("error");
	 Date date = util.getCurrentDate();
	 List<AreaWisedetails> areaWisedetailsList = null;
	String selectedArea = request.getParameter("selectedArea");
	String selectedProduct = request.getParameter("keyword");
	if(StringUtils.isBlank(selectedArea)) {
		selectedArea ="Powai";
	}
	if(StringUtils.isBlank(selectedProduct)) {
	 
	  areaWisedetailsList = service.searchByAreaAndDate(selectedArea,date );
	}
	else {
	 areaWisedetailsList = service.searchByAreaNameAndDateAndProductName(selectedArea, date, selectedProduct);
		
	}
	 List<DailyProductDetails> dailyproductList = productDetailservice.search(util.getCurrentDate());
	 List<Products> listProducts = productService.listAll();
	 List<Area> listAreas = areaService.listAll();
	 
	 List<AreaWisedetails> areaWisedetailsList2 = new ArrayList<AreaWisedetails>();
	 for(DailyProductDetails pdetails:dailyproductList) {
		 AreaWisedetails details = new AreaWisedetails();
		 details.setProductName(pdetails.getProductName());
		 details.setPurchasePrice(pdetails.getPurchasePrice());
		 details.setSellingprice(pdetails.getSellingprice());
		 details.setDate(pdetails.getDate());
		 areaWisedetailsList2.add(details);
	 }
	 
	
	 List<String> dailyproducts = dailyproductList.stream().map(DailyProductDetails::getProductName).collect(Collectors.toList());
	 
	 
		   ModelAndView mav = new ModelAndView("AreaWiseDailyUpdates");
		   mav.addObject("listDetails", dailyproductList); 
		   mav.addObject("listProducts", listProducts);
		   
		   mav.addObject("products", dailyproducts);
		   mav.addObject("Area", listAreas); 
		   if(null == areaWisedetailsList ||areaWisedetailsList.isEmpty()) {
			   mav.addObject("areaWisedetailsList", areaWisedetailsList2);
			 }
		   else {
		   mav.addObject("areaWisedetailsList", areaWisedetailsList);
		   }
		   mav.addObject("areaWisedetailsList2", areaWisedetailsList2);
		   mav.addObject("selectedArea", selectedArea);
		   if(!StringUtils.isBlank(selectedProduct)) {
		   mav.addObject("selectedProduct", selectedProduct);
		   }
		   if(null!= error) {
	        	mav.addObject("error", error);
	        }
		   return mav;
		 
    }
 
	
	  @RequestMapping(value = "/saveAreaProductRecord", method = RequestMethod.POST)
	  public String saveProductRecord(HttpServletRequest request,
	  HttpServletResponse response) {
		String  selectedArea =  request.getParameter("selectedArea");
	  Date date = util.getCurrentDate();
	  
	  String[] selectedItems = request.getParameterValues("selected");
	  
	  for (String selectedItem : selectedItems) {
		  
		  if(util.checkDuplicateAreaDailyPrdct(selectedArea,request.getParameter("productName" + selectedItem))) {
				return "redirect:/areaWisedetails?error='duplicate'";
			}
	  
		  AreaWisedetails record = new AreaWisedetails();
	  if(!StringUtils.isBlank(request.getParameter("id"+selectedItem))) {
	  record.setId(Long.parseLong(request.getParameter("id"+selectedItem))); }
	  
	  record.setAreaName(selectedArea);
	  record.setProductName(request.getParameter("productName"+selectedItem));
	  record.setPurchasePrice(Integer.parseInt(request.getParameter("purchasePrice"
	  +selectedItem)));
	  record.setQuantity(Integer.parseInt(request.getParameter("quantity"+
	  selectedItem)));
	  record.setSellingprice(Integer.parseInt(request.getParameter("sellingprice"+
	  selectedItem))); 
	  record.setDate(date);
	  record.setReceivedAmount(Integer.parseInt(request.getParameter("receivedAmount"+selectedItem)));
	  record.setReasonForReturn(request.getParameter("reasonForReturn"+selectedItem));
	  record.setReturnedQuantity(Integer.parseInt(request.getParameter("returnedQuantity"+selectedItem)));
	  service.save(record); 
	  }
	  
	  return "redirect:/areaWisedetails"; }
	  
	  
	@RequestMapping("/deleteAreaProductRecord")
	public String deleteProduct(@RequestParam long id) {
		service.delete(id);
		return "redirect:/areaWisedetails";
	}
	  
   

}
