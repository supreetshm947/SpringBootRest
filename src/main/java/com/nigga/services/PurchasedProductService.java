package com.nigga.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nigga.model.Discount;
import com.nigga.model.PurchasedProduct;
import com.nigga.repos.DiscountRepository;
import com.nigga.repos.PurchasedProductRepository;

@Service
public class PurchasedProductService {
	@Autowired
	private PurchasedProductRepository purProdRepository;
	
	@Autowired
	private DiscountRepository discountRepository;
	
	public PurchasedProduct createPurchasedProductRepository(String productId, String description, BigDecimal originalPrice, BigDecimal finalPrce,
			String discountId){
		Discount d = discountRepository.findOne(discountId);
		if(d!=null){
			return purProdRepository.save(new PurchasedProduct(productId, description, originalPrice, finalPrce, d));
		}
		return null;
	}
	
	public Iterable<PurchasedProduct> lookup(){
		return purProdRepository.findAll();
	}
}
