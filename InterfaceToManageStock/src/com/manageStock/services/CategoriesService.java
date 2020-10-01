package com.manageStock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manageStock.model.Categories;
import com.manageStock.repositories.CategoriesRepository;

@Service
@Transactional
public class CategoriesService {
	
	@Autowired CategoriesRepository repo;
	
	 public void save(Categories categories) {
	        repo.save(categories);
	    }
	     
	    public List<Categories> listAll() {
	        return (List<Categories>) repo.findAllByOrderByIdAsc();
	    }
	    
	    public List<Categories> findByCategory_name(String keyword) {
	        return (List<Categories>) repo.findByCategoryName(keyword);
	    }

		public void delete(long id) {
			 repo.deleteById(id);
		}
		
		public List<Categories> findByCategory_code(Integer code) {
	        return (List<Categories>) repo.findByCategoryCode(code);
	    }

}
