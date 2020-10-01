package com.manageStock.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manageStock.model.DailyProfit;

public interface DailyProfitRepository extends CrudRepository<DailyProfit, Long>{
	
List<DailyProfit> findByDateOrderByIdAsc(Date date);
	
	List<DailyProfit> findByAreaNameAndDate(String areaName,Date date);

}
