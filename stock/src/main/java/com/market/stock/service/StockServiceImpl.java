package com.market.stock.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.market.stock.model.CompanyNotFoundException;
import com.market.stock.model.Stock;
import com.market.stock.repository.StockRepo;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockRepo stockRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Stock addStock(Stock stock) throws CompanyNotFoundException{
		String url ="http://localhost:1111/api/v1.0/market/company/" + stock.getCompanyCode();
		if(HttpStatus.OK.equals(restTemplate.exchange(url, HttpMethod.GET, null, String.class).getStatusCode())) {
		stock.setStartDate(new Date());
		return stockRepo.save(stock);}
		else {
			throw new CompanyNotFoundException("Company with code"+ stock.getCompanyCode() + "is not avilable");
		}
	}

	@Override
	public List<Stock> getStockInDateRange(String companyCode, Date startDate, Date endDate) {
		
		return stockRepo.stockInRange(companyCode,startDate,endDate);
	}
	

	@Override
	public List<Stock> getStockByCompanyCode(String code) {
		return stockRepo.findByCompanyCode(code);
	}
	
	@Override
	public Long deleteStockWithCode(String code){
		return stockRepo.deleteStockByCompanyCode(code);
	}
}
