package com.manageStock.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manageStock.model.DailyProductDetails;

public interface DailyProductdetailsRepository extends CrudRepository<DailyProductDetails, Long>{
	
	List<DailyProductDetails> findByDateOrderByIdAsc(Date date);
	
	List<DailyProductDetails> findByProductNameAndDate(String product,Date date);

}
