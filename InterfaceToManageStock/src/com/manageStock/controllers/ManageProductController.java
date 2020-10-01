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

import com.manageStock.model.Products;
import com.manageStock.services.ProductService;
import com.manageStock.util.UtilityClass;

@Controller
public class ManageProductController {
	
    @Autowired
	private ProductService productService;
    
    @Autowired
	private UtilityClass util;
    
    @RequestMapping("/")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
    	String error = request.getParameter("error");
        List<Products> listProducts = productService.listAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listProducts", listProducts);
        if(null!= error) {
        	mav.addObject("error", error);
        }
        return mav;
    }
    
    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(HttpServletRequest request, HttpServletResponse response) { 	
    	String[] selectedItems = request.getParameterValues("selected");
    	for (String selectedItem : selectedItems) {
    		Products products = new Products();
    		if(util.checkDuplicateProduct(request.getParameter(selectedItem))) {
    			return "redirect:/?error='duplicate'";
    		}
    		products.setProductName(request.getParameter(selectedItem));
    		productService.save(products);
    	}
    	
        return "redirect:/";
    }
    
    
    @RequestMapping("/editProduct")
    public ModelAndView editProductForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Products product = productService.get(id);
        mav.addObject("product", product);
     
        return mav;
    }

    @RequestMapping(value = "/saveEditedProduct", method = RequestMethod.POST)
    public String saveEditedProduct(@ModelAttribute("product") Products product) {
    	
    	if(util.checkDuplicateProduct(product.getProductName())) {
			return "redirect:/?error='duplicate'";
		}
    	productService.save(product);
        return "redirect:/";
    }
    
    @RequestMapping("/deleteProduct")
    public String deleteProduct(@RequestParam long id) {
        productService.delete(id);
        return "redirect:/";       
    }
    
    @RequestMapping("/searchProduct")
    public ModelAndView searchProduct(@RequestParam String keyword) {
        List<Products> result = productService.search(keyword);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("listProducts", result);
        return mav;
     
    }
   
}
