package com.manageStock.controllers;

import java.util.ArrayList;
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

import com.manageStock.model.Categories;
import com.manageStock.services.CategoriesService;
import com.manageStock.util.UtilityClass;

@Controller
public class CategoriesController {

	@Autowired CategoriesService service;
	
	 @Autowired
		private UtilityClass util;
	
	@RequestMapping("/ListCategories")
    public ModelAndView areaHome(HttpServletRequest request, HttpServletResponse response) {
    	String error = request.getParameter("error");
    	
    	String cname =request.getParameter("keyword");
    	
    	 List<Categories> listCategories = new ArrayList<Categories>();
    	
    	 if (StringUtils.isBlank(cname)) {
    		 listCategories = service.listAll();
    	}
    	else {
    		listCategories = service.findByCategory_name(cname);
    	}
        ModelAndView mav = new ModelAndView("category");
        mav.addObject("listCategories", listCategories);
        if(null!= error) {
        	mav.addObject("error", error);
        }
        return mav;
    }
	

	  @RequestMapping(value = "/saveCategories", method = RequestMethod.POST)
	  public String saveProductRecord(HttpServletRequest request,
	  HttpServletResponse response) {
	  
	  String[] selectedItems = request.getParameterValues("selected");
	  
	  for (String selectedItem : selectedItems) {
	  
		  
		  if(util.checkDuplicateCategor(request.getParameter("category_name" + selectedItem),Integer.parseInt(request.getParameter("category_code" + selectedItem)))) {
				return "redirect:/ListCategories?error='duplicate'";
			}
		  Categories record = new Categories();
		  
	  if(!StringUtils.isBlank(request.getParameter("id"+selectedItem))) {
	  record.setId(Long.parseLong(request.getParameter("id"+selectedItem)));
	  }
	  record.setCategoryCode(Integer.parseInt(request.getParameter("categoryCode" + selectedItem)));
	  record.setCategoryName(request.getParameter("categoryName" + selectedItem));
	  service.save(record); 
	  }
	  
	  return "redirect:/ListCategories"; 
	  }
	  
	  
	@RequestMapping("/deleteCategoryRecord")
	public String deleteCategoryRecord(@RequestParam long id) {
		service.delete(id);
		return "redirect:/ListCategories";
	}
	  
    
	
}
