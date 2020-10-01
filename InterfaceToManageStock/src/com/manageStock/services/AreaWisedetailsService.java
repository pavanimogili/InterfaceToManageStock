package com.manageStock.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageStock.model.AreaWisedetails;
import com.manageStock.repositories.AreaWisedetailsRepository;

@Service
public class AreaWisedetailsService {
	
	@Autowired AreaWisedetailsRepository repo;
	
	 public void save(AreaWisedetails area) {
	        repo.save(area);
	    }
	     
	    public List<AreaWisedetails> listAll() {
	        return (List<AreaWisedetails>) repo.findAll();
	    }
	     
	    public AreaWisedetails get(Long id) {
	        return repo.findById(id).get();
	    }
	     
	    public void delete(Long id) {
	        repo.deleteById(id);
	    }
	    
	    public List<AreaWisedetails> searchByAreaAndDate(String Area,Date date) {
	        return repo.findByAreaNameAndDateOrderByIdAsc(Area,date);
	    }
	    
	    public List<AreaWisedetails> searchByAreaNameAndDateAndProductName(String Area,Date date,String product) {
	        return repo.findByAreaNameAndDateAndProductName(Area,date,product);
	    }
	    
	    public List<AreaWisedetails> searchByDate(Date date) {
	        return repo.findByDateOrderByIdAsc(date);
	    }

}
