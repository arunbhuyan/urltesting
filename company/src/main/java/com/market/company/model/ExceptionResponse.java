package com.market.company.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ExceptionResponse {
	@Getter
	private Date timestamp;
	
	@Getter
	private String message;
	
	@Getter
	private String details;
	
}
