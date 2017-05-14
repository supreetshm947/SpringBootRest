package com.nigga.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nigga.model.Discount;
import com.nigga.repos.DiscountRepository;

@Service
public class DiscountService {
	@Autowired
	DiscountRepository repository;
	public Discount createDiscount(String discountId, String type, String description, BigDecimal amount, String productId){
		if(repository.exists(discountId)){
			return null;
		}else{
			return repository.save(new Discount(discountId, type, description, amount, productId));
		}
	}
	
	public Iterable<Discount> lookup(){
		return repository.findAll();
	}

}
