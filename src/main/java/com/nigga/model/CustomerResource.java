package com.nigga.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Gulu
 *
 */
@Entity
public class CustomerResource {
	@Id
	@Column
	private String uuid;
	@Column
	private String name;
	@Column
	private String address;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany
	private List<Discount> eligibleDiscounts;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany
	private List<PurchasedProduct> products;
	
	public CustomerResource() {
		super();
	}
	public CustomerResource(String uuid, String name, String address, List<Discount> eligibleDiscounts,
			List<PurchasedProduct> products) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.address = address;
		this.eligibleDiscounts = eligibleDiscounts;
		this.products = products;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Discount> getEligibleDiscounts() {
		return eligibleDiscounts;
	}
	public void setEligibleDiscounts(List<Discount> eligibleDiscounts) {
		this.eligibleDiscounts = eligibleDiscounts;
	}
	public List<PurchasedProduct> getProducts() {
		return products;
	}
	public void setProducts(List<PurchasedProduct> products) {
		this.products = products;
	}
	
}
