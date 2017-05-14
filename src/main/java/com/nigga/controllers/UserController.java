package com.nigga.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nigga.model.CustomerResource;
import com.nigga.services.CustomerResourceService;
import com.nigga.services.ErrorResourceService;

@RestController
@RequestMapping(path="/rest/v1/customers/{user}")
public class UserController {
	@Autowired
	private CustomerResourceService custService;
	
	@Autowired
	private ErrorResourceService errService;
	
	@RequestMapping(method=RequestMethod.GET)
	public CustomerResource getCustByUuid(@PathVariable("user") String user) throws UserNotFoundException{
		CustomerResource cust = verifyCustomer(user);
		return cust;
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
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Object return400(UserNotFoundException ex) {
        return errService.getByCode("ER0002");

    }
}
