package com.market.stock.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.market.stock.model.Stock;

@Repository
public interface StockRepo extends MongoRepository<Stock, String>{

	@Query("{'companyCode': ?0  , 'startDate' : { $gte: ?1, $lte: ?2 } }")
	List<Stock> stockInRange(String companyCode, Date startDate, Date endDate);

	List<Stock> findByCompanyCode(String code);
	
	Long deleteStockByCompanyCode(String code);

}
