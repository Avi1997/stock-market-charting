package com.cognizant.stockmarket.excelupload.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.stockmarket.excelupload.model.StockPrice;
import com.cognizant.stockmarket.excelupload.service.StockPriceService;

@RestController
@RequestMapping("/stock-market")
public class GraphController {
	@Autowired
	StockPriceService stockPriceService;

	@GetMapping("/stock-details/{companyCode}")
	List<StockPrice> getAllStockPrice(@PathVariable long companyCode) {
		return stockPriceService.getAllStrockPrice(companyCode);
	}
	
	@GetMapping("/stock-exchange-list/{companyCode}")
	public List<StockPrice> getStockExchangeList(@PathVariable Long companyCode) {
		return stockPriceService.getStockExchangesList(companyCode);
	}
	
	

}
