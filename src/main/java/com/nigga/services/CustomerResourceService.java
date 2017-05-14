package com.nigga.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nigga.model.CustomerResource;
import com.nigga.model.Discount;
import com.nigga.model.PurchasedProduct;
import com.nigga.repos.CustomerResourceRepository;
import com.nigga.repos.DiscountRepository;
import com.nigga.repos.PurchasedProductRepository;

@Service
public class CustomerResourceService {

	@Autowired
	private CustomerResourceRepository custResRepo;
	
	@Autowired
	private DiscountRepository disRepo;
	
	@Autowired 
	private PurchasedProductRepository prodRepo;
	
	public CustomerResource createCustomerResource(String uuid, String name, String address,
		List<String> discountIds, List<String> productIds){
		List<Discount> eligDisc = new ArrayList<>();
		List<PurchasedProduct> prods = new ArrayList<>();
		for(String d : discountIds){
			Discount dis = disRepo.findOne(d);
			if(dis!=null){
				eligDisc.add(dis);
			}else
				return null;
		}
		for(String p : productIds){
			PurchasedProduct prod = prodRepo.findOne(p);
			if(prod!=null){
				prods.add(prod);
			}else
				return null;
		}
		return custResRepo.save(new CustomerResource(uuid, name, address, eligDisc, prods));
	}
	
	public CustomerResource findByUuid(String uuid){
		return custResRepo.findByUuid(uuid);
	}
	
	public Iterable<CustomerResource> lookup(){
		return custResRepo.findAll();
	}
}
