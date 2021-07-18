package com.market.company.service;


import java.util.List;

import com.market.company.model.Company;


public interface CompanyService {
	Company addNewCompany(Company company);
	void deleteCompany(String id);
	Company updateNewCompany(Company comapny);
	List<Company> getAllCompany();
	Company getCompany(String id);
	boolean hasCompany(String id);
}
