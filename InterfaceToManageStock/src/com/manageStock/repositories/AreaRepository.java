package com.manageStock.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manageStock.model.Area;

public   interface AreaRepository extends CrudRepository<Area, Long>{
	
	List<Area> findByAreaName(String areaName);

	List<Area> findAllByOrderByAreaIdAsc();
}
