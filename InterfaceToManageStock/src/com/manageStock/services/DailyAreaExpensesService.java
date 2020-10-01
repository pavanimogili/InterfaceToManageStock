package com.manageStock.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manageStock.model.DailyAreaExpenses;
import com.manageStock.repositories.DailyAreaExpensesRepository;

@Service
@Transactional
public class DailyAreaExpensesService {
	
	@Autowired DailyAreaExpensesRepository repo;
	
	public List<DailyAreaExpenses> search(Date date) {
        return repo.findByDateOrderByIdAsc(date);
    }
 
 public void save(DailyAreaExpenses record) {
        repo.save(record);
    }

public void delete(long id) {
	repo.deleteById(id);
}

public List<DailyAreaExpenses> search(String keyword,Date date) {
	return repo.findByAreaNameAndDate(keyword,date);
}


}
