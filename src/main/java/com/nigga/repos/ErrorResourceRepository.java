package com.nigga.repos;

import org.springframework.data.repository.CrudRepository;

import com.nigga.model.ErrorResource;

public interface ErrorResourceRepository extends CrudRepository<ErrorResource, String> {
	public ErrorResource getByCode(String code);
}
