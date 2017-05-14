package com.nigga.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nigga.model.CustomerResource;

public interface CustomerResourceRepository extends CrudRepository<CustomerResource, String> {
	CustomerResource findByUuid(@Param("uuid")String uuid);
}
