package com.manageStock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manageStock.model.Products;
import com.manageStock.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired ProductRepository repo;
    
    public void save(Products products) {
        repo.save(products);
    }
     
    public List<Products> listAll() {
        return (List<Products>) repo.findAllByOrderByProductIdAsc();
    }
     
    public Products get(Long id) {
        return repo.findById(id).get();
    }
     
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    public List<Products> search(String keyword) {
        return repo.findByProductName(keyword);
    }
}
