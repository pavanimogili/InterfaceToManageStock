package com.manageStock.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manageStock.model.Categories;


public interface CategoriesRepository extends CrudRepository<Categories, Long> {
	
	List<Categories> findByCategoryName(String name);

	List<Categories> findAllByOrderByIdAsc();

	List<Categories> findByCategoryCode(Integer code);
}
