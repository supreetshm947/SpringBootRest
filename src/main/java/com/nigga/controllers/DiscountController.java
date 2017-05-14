package com.nigga.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nigga.model.CustomerResource;
import com.nigga.model.Discount;
import com.nigga.model.PurchasedProduct;
import com.nigga.services.CustomerResourceService;
import com.nigga.services.ErrorResourceService;
import com.nigga.services.PurchasedProductService;

@RestController
@RequestMapping(path="/rest/v1/users/{user}")
public class DiscountController {
	
	@Autowired
	private CustomerResourceService custService;
	
	@Autowired
	private ErrorResourceService errService;
	
	@RequestMapping(method=RequestMethod.GET, path="/discounts")
	public List<Discount> getDiscountByUser(@PathVariable("user") String user, 
			@RequestParam(name="productId", required=false) String productId) throws UserNotFoundException, NoDiscountFoundException{
		CustomerResource cust = verifyCustomer(user);
		if(productId!=null){
			for(PurchasedProduct p : cust.getProducts()){
				if(p.getProductId().equals(productId)){
					return Collections.singletonList(p.getDiscountInformation());
				}
			}
			throw new NoDiscountFoundException();
		}
		return cust.getEligibleDiscounts();
	}
	
	public CustomerResource verifyCustomer(String uuid) throws UserNotFoundException{
		CustomerResource cust = custService.findByUuid(uuid);
		if(cust == null){
			throw new UserNotFoundException();
		}
		return cust;
	}
	
	class UserNotFoundException extends Exception{
		public UserNotFoundException() {
			super();
		}
	}
	
	class NoDiscountFoundException extends Exception{
		public NoDiscountFoundException() {
			super();
		}
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Object return400(UserNotFoundException ex) {
        return errService.getByCode("ER0002");

    }
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoDiscountFoundException.class)
    public Object return400(NoDiscountFoundException ex) {
        return errService.getByCode("ER0001");

    }

}
