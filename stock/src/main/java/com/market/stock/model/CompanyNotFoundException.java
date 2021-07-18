package com.market.stock.model;

public class CompanyNotFoundException extends RuntimeException{
	
	public CompanyNotFoundException(String message) {
		super(message);
	}
}
