package com.nigga.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ErrorResource {
	@Id
	@Column
	private String code;
	@Column
	private String msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ErrorResource(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public ErrorResource() {
		super();
	}
	
}
