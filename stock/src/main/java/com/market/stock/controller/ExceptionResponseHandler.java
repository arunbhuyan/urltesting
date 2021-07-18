package com.market.stock.controller;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.market.stock.model.CompanyNotFoundException;
import com.market.stock.model.ExceptionResponse;

@RestControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CompanyNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleCompanyNotFoundExceptions(Exception ex, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String fieldErrorDescription = ex.getBindingResult()
				.getFieldErrors().stream()
				.map(error -> error.getField() + " -> " + error.getDefaultMessage())
				.collect(Collectors.joining(" , "));
		ExceptionResponse exceptionResponse =new ExceptionResponse(new Date(),"Validation failed", fieldErrorDescription);
		return new ResponseEntity<Object>(exceptionResponse, status);
	}
	
}
