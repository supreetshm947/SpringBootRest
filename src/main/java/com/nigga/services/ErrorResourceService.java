package com.nigga.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nigga.model.ErrorResource;
import com.nigga.repos.ErrorResourceRepository;

@Service
public class ErrorResourceService {
	@Autowired
	private ErrorResourceRepository errorRepo;
	
	public ErrorResource createErrorResource(String code, String msg){
		if(errorRepo.exists(code)){
			return null;
		}else{
			return errorRepo.save(new ErrorResource(code, msg));
		}
	}
	
	public Iterable<ErrorResource> lookup(){
		return errorRepo.findAll();
	}
	
	public ErrorResource getByCode(String code){
		return errorRepo.getByCode(code);
	}

}
