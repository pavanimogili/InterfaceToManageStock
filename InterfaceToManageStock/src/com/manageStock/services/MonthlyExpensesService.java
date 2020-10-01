package com.manageStock.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageStock.model.MonthlyExpenses;
import com.manageStock.repositories.MonthlyExpensesRepository;

@Service
public class MonthlyExpensesService {

	@Autowired MonthlyExpensesRepository repo;
	
	 public List<MonthlyExpenses> search(Date date) {
	        return repo.findByDateOrderByIdAsc(date);
	    }
	 
	 public void save(MonthlyExpenses record) {
	        repo.save(record);
	    }

	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<MonthlyExpenses> search(String keyword,Date date) {
		return repo.findByAreaNameAndDate(keyword,date);
	}
}
