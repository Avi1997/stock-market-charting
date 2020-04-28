package com.cognizant.stockmarket.excelupload.service;

import java.util.List;

import com.cognizant.stockmarket.excelupload.model.StockPrice;

public interface StockPriceService {
	public List<StockPrice> getAllStrockPrice(long companyCode);
	public List<StockPrice> getStockExchangesList(Long companyCode);
}
