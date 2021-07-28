package com.market.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.market.company.model.Company;
import com.market.company.repository.CompanyRepo;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyRepo companyRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${STOCK_URI:http://localhost}")
	private String stockUri;
	
	@Override
	public Company addNewCompany(Company company) {
		// TODO Auto-generated method stub
		return companyRepo.save(company);
	}

	@Override
	public void deleteCompany(String id) {
		// TODO Auto-generated method stub
		String url = stockUri + ":2222/api/v1.0/market/stock/delete/"+ id;
		if(HttpStatus.OK.equals(restTemplate.exchange(url, HttpMethod.DELETE, null,String.class).getStatusCode()))
		companyRepo.deleteById(id);
	}

	@Override
	public Company updateNewCompany(Company company) {
		// TODO Auto-generated method stub
		return companyRepo.save(company);
	}
	
	@Override
	public List<Company> getAllCompany() {
		// TODO Auto-generated method stub
		return companyRepo.findAll();
	}


	@Override
	public Company getCompany(String id) {
		// TODO Auto-generated method stub
		return companyRepo.findById(id).get();
	}

	@Override
	public boolean hasCompany(String id) {
		// TODO Auto-generated method stub
		return companyRepo.findById(id).isPresent() ? Boolean.TRUE : Boolean.FALSE;
	}
}
