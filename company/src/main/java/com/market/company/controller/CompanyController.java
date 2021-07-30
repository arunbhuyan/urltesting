package com.market.company.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.company.model.Company;
import com.market.company.service.CompanyService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1.0/market/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@PostMapping(path = "/register")
	@ApiOperation(value = "Register new company")
	public ResponseEntity<Company> registerCompany(@Valid @RequestBody Company company){
		return new ResponseEntity<Company>(companyService.addNewCompany(company), HttpStatus.CREATED);  
	}
	
	@DeleteMapping(path = "/delete/{id}")
	@ApiOperation(value = "Delete company with companyCode")
	public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") String id){
		companyService.deleteCompany(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/info/{id}")
	@ApiOperation(value = "Get comapny details with companyCode")
	public ResponseEntity<Company> getCompany(@PathVariable String id){
		return new ResponseEntity<Company>(companyService.getCompany(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getall")
	@ApiOperation(value = "Get all registered company")
	public ResponseEntity<List<Company>> getAllCompany(){
		return new ResponseEntity<List<Company>>(companyService.getAllCompany(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Check if comapny  exits with companyCode")
	public ResponseEntity<?> hasCompany(@PathVariable String id){
		return new ResponseEntity<>(companyService.hasCompany(id)? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}
