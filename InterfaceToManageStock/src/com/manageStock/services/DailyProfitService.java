package com.manageStock.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manageStock.model.DailyProfit;
import com.manageStock.repositories.DailyProfitRepository;

@Service
public class DailyProfitService {
	
@Autowired DailyProfitRepository repo;

public List<DailyProfit> search(Date date) {
    return repo.findByDateOrderByIdAsc(date);
}

public void save(DailyProfit record) {
    repo.save(record);
}

public void delete(long id) {
repo.deleteById(id);
}

public List<DailyProfit> search(String keyword,Date date) {
return repo.findByAreaNameAndDate(keyword,date);
}

}
