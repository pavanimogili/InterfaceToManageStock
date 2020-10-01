package com.manageStock.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manageStock.model.DailyProductDetails;
import com.manageStock.repositories.DailyProductdetailsRepository;

@Service
@Transactional
public class DailyproductdetailsService {
	
	@Autowired DailyProductdetailsRepository repo;
	
	 public List<DailyProductDetails> search(Date date) {
	        return repo.findByDateOrderByIdAsc(date);
	    }
	 
	 public void save(DailyProductDetails record) {
	        repo.save(record);
	    }

	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<DailyProductDetails> search(String keyword,Date date) {
		return repo.findByProductNameAndDate(keyword,date);
	}

}
