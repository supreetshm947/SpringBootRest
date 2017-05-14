package com.nigga.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Discount {
	@Id
	@Column
	private String discountId;
	@Column
	private String type;
	@Column
	private String description;
	@Column
	private BigDecimal amount;
	@Column
	private String productId;
	
	public Discount() {
		super();
	}
	public Discount(String discountId, String type, String description, BigDecimal amount, String productId) {
		super();
		this.discountId = discountId;
		this.type = type;
		this.description = description;
		this.amount = amount;
		this.productId = productId;
	}
	public String getDiscountId() {
		return discountId;
	}
	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
