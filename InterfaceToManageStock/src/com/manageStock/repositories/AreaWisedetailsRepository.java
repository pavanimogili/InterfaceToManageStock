package com.manageStock.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manageStock.model.AreaWisedetails;

public interface AreaWisedetailsRepository extends CrudRepository<AreaWisedetails, Long> {
	
	List<AreaWisedetails> findByAreaNameAndDateOrderByIdAsc(String area,Date date);
	
	List<AreaWisedetails> findByAreaNameAndDateAndProductName(String area,Date date,String product);
	
	List<AreaWisedetails> findByDateOrderByIdAsc(Date date);
}
