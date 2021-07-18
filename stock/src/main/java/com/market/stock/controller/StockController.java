package com.market.stock.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.client.RestTemplate;

import com.market.stock.model.Stock;
import com.market.stock.service.StockService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/api/v1.0/market/stock")
public class StockController {
	@Autowired
	private StockService stockService;
	
	@GetMapping
	public ResponseEntity<?> get(){
		//logger.warn("companyCode");
		return new ResponseEntity<String>("test", HttpStatus.OK);
	}
	
	@PostMapping(value = "/add/{companyCode}")
	public ResponseEntity<?> newStock(@RequestBody Stock stock, @PathVariable(name="companyCode") Integer code){
		
		return new ResponseEntity<Stock>(stockService.addStock(stock), HttpStatus.CREATED) ;
	}
	
	@GetMapping(value = "/get/{companyCode}/{startDate}/{endDate}")
	public ResponseEntity<?> getStocks(
			@PathVariable(name="companyCode") String companyCode,
			@PathVariable(name="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@PathVariable(name="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

		return new ResponseEntity<List<Stock>>(stockService.getStockInDateRange(companyCode, startDate, endDate),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{companyCode}")
	public ResponseEntity<?> deleteStockWithCode(@PathVariable(name="companyCode") String companyCode){
		return new ResponseEntity<Long>(stockService.deleteStockWithCode(companyCode), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/{companyCode}")
	public ResponseEntity<?> getStocks(@PathVariable(name="companyCode") String companyCode) {
		return new ResponseEntity<List<Stock>>(stockService.getStockByCompanyCode(companyCode),HttpStatus.OK);
	}
}
