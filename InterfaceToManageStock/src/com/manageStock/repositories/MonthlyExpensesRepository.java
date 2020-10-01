package com.manageStock.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manageStock.model.MonthlyExpenses;

public interface MonthlyExpensesRepository extends CrudRepository<MonthlyExpenses, Long>{
	
List<MonthlyExpenses> findByDateOrderByIdAsc(Date date);
	
	List<MonthlyExpenses> findByAreaNameAndDate(String area,Date date);

}
