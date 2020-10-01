package com.manageStock.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.manageStock.model.Area;
import com.manageStock.services.AreaService;
import com.manageStock.util.UtilityClass;

@Controller
public class ManageAreaController {

	 @Autowired
		private AreaService areaService;
	 
	 @Autowired
		private UtilityClass util;
	

    @RequestMapping("/ListAreas")
    public ModelAndView areaHome(HttpServletRequest request, HttpServletResponse response) {
    	String error = request.getParameter("error");
        List<Area> listArea = areaService.listAll();
        ModelAndView mav = new ModelAndView("area");
        mav.addObject("listArea", listArea);
        if(null!= error) {
        	mav.addObject("error", error);
        }
        return mav;
    }
    
    @RequestMapping(value = "/saveArea", method = RequestMethod.POST)
    public String saveProduct(HttpServletRequest request, HttpServletResponse response) { 	
    	String[] selectedItems = request.getParameterValues("selected");
		
    	 
    	for (String selectedItem : selectedItems) {
			
    		if(util.checkDuplicateArea(request.getParameter(selectedItem))) {
    			return "redirect:/ListAreas?error='duplicate'";
    		}
    		Area areas = new Area();
    		areas.setAreaName(request.getParameter(selectedItem));
    		areaService.save(areas);
    	}
    	
        return "redirect:/ListAreas";
    }
    
    
    @RequestMapping("/editArea")
    public ModelAndView editProductForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("edit_area");
        Area area = areaService.get(id);
        mav.addObject("area", area);
     
        return mav;
    }

    @RequestMapping(value = "/saveEditedArea", method = RequestMethod.POST)
    public String saveEditedProduct(@ModelAttribute("area") Area area) {
    	
    	if(util.checkDuplicateArea(area.getAreaName())) {
			return "redirect:/ListAreas?error='duplicate'";
		}
    	
    	areaService.save(area);
        return "redirect:/ListAreas";
    }
    
    @RequestMapping("/deleteArea")
    public String deleteProduct(@RequestParam long id) {
        areaService.delete(id);
        return "redirect:/ListAreas";       
    }
    
    @RequestMapping("/searchArea")
    public ModelAndView searchProduct(@RequestParam String keyword) {
        List<Area> result = areaService.search(keyword);
        ModelAndView mav = new ModelAndView("area");
        mav.addObject("listArea", result);
        return mav;
     
    }
   
}
