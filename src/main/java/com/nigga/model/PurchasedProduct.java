package com.nigga.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PurchasedProduct {
	@Id
	@Column
	private String productId;
	@Column
	private String description;
	@Column
	private BigDecimal originalPrice;
	@Column
	private BigDecimal finalPrice;
	
	@OneToOne
	private Discount discountInformation;
	
	public PurchasedProduct() {
		super();
	}

	public PurchasedProduct(String productId, String description, BigDecimal originalPrice, BigDecimal finalPrce,
			Discount discountInformation) {
		super();
		this.productId = productId;
		this.description = description;
		this.originalPrice = originalPrice;
		this.finalPrice = finalPrce;
		this.discountInformation = discountInformation;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getFinalPrce() {
		return finalPrice;
	}

	public void setFinalPrce(BigDecimal finalPrce) {
		this.finalPrice = finalPrce;
	}

	public Discount getDiscountInformation() {
		return discountInformation;
	}

	public void setDiscountInformation(Discount discountInformation) {
		this.discountInformation = discountInformation;
	}
	
}
