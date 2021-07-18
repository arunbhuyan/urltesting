package com.market.stock.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.market.stock.model.Stock;

public interface StockService {

	Stock addStock(Stock stock);

	List<Stock> getStockInDateRange(String companyCode, Date startDate, Date endDate);
	
	Long deleteStockWithCode(String companyCode);
	
	List<Stock> getStockByCompanyCode(String code);

}
