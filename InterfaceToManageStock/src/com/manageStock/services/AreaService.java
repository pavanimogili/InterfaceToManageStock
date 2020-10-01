package com.manageStock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manageStock.model.Area;
import com.manageStock.repositories.AreaRepository;


@Service
@Transactional
public class AreaService {

	
@Autowired AreaRepository repo;
    
    public void save(Area area) {
        repo.save(area);
    }
     
    public List<Area> listAll() {
        return (List<Area>) repo.findAllByOrderByAreaIdAsc();
    }
     
    public Area get(Long id) {
        return repo.findById(id).get();
    }
     
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    public List<Area> search(String keyword) {
        return repo.findByAreaName(keyword);
    }
}
