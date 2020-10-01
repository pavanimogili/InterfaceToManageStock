package com.manageStock.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manageStock.model.DailyAreaExpenses;

public interface DailyAreaExpensesRepository extends CrudRepository<DailyAreaExpenses, Long>{
	
	List<DailyAreaExpenses> findByDateOrderByIdAsc(Date date);
	
	List<DailyAreaExpenses> findByAreaNameAndDate(String areaName,Date date);

}
