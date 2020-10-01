package com.manageStock.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manageStock.model.Products;

public interface ProductRepository extends CrudRepository<Products, Long>{
	
	List<Products> findByProductName(String productName);
	
	 List<Products> findAllByOrderByProductIdAsc();
	
}
