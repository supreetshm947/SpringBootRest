package com.nigga;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nigga.services.CustomerResourceService;
import com.nigga.services.DiscountService;
import com.nigga.services.ErrorResourceService;
import com.nigga.services.PurchasedProductService;

@SpringBootApplication
public class RestdemoApplication implements CommandLineRunner{

	@Autowired
	DiscountService disService;
	
	@Autowired
	ErrorResourceService errResService;

	@Autowired
	PurchasedProductService purProdService;
	
	@Autowired
	CustomerResourceService cusResService;
	
	public static void main(String[] args) {
		SpringApplication.run(RestdemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DiscountFromFile.importDiscounts().forEach(d -> disService.createDiscount(d.discountId, d.type, d.description, d.amount, d.productId));
		disService.lookup().forEach(d -> System.out.println(d.getDiscountId()));
		
		ErrorRescourceFromFile.importErrorResource().forEach(e -> errResService.createErrorResource(e.code, e.msg));
		errResService.lookup().forEach(e -> System.out.println(e.getCode()));
		
		PurchasedProductFromFile.importPurchasedProduct().forEach(p -> purProdService.createPurchasedProductRepository(p.productId, p.description, p.originalPrice, p.finalPrice, p.discountId));
		purProdService.lookup().forEach(p -> System.out.println(p.getProductId()));
		
		CustomerResourceFromFile.importCustomerResource().forEach(c -> cusResService.createCustomerResource(c.uuid, c.name, c.address, c.discountIds, c.productIds));
		cusResService.lookup().forEach(c -> System.out.println(cusResService.findByUuid(c.getUuid()).getUuid()));
	}
	
	static class DiscountFromFile {
		//attributes as listed in the .json file
		private String discountId, type ,description , productId;
		private BigDecimal amount;

		/**
		 * Open the discounts.json, unmarshal every entry into a DiscountFromFile Object.
		 *
		 * @return a List of DiscountFromFile objects.
		 * @throws IOException if ObjectMapper unable to open file.
         */
		static List<DiscountFromFile> importDiscounts() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
					readValue(DiscountFromFile.class.getResourceAsStream("/discounts.json"),new TypeReference<List<DiscountFromFile>>(){});
		}
	}
	
	static class ErrorRescourceFromFile {
		
		private String code, msg;
		
		static List<ErrorRescourceFromFile> importErrorResource() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
					readValue(ErrorRescourceFromFile.class.getResourceAsStream("/errorResource.json"),new TypeReference<List<ErrorRescourceFromFile>>(){});
		}
	}
	
	static class PurchasedProductFromFile {
		
		private String productId, description, discountId;

		private BigDecimal originalPrice, finalPrice;
		
		static List<PurchasedProductFromFile> importPurchasedProduct() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
					readValue(PurchasedProductFromFile.class.getResourceAsStream("/purchasedProduct.json"),new TypeReference<List<PurchasedProductFromFile>>(){});
		}
	}
	
	static class CustomerResourceFromFile {
		
		private String uuid, name, address;
		
		private List<String> discountIds, productIds;
		
		static List<CustomerResourceFromFile> importCustomerResource() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
					readValue(CustomerResourceFromFile.class.getResourceAsStream("/customerResource.json"),new TypeReference<List<CustomerResourceFromFile>>(){});
		}
	}

}
