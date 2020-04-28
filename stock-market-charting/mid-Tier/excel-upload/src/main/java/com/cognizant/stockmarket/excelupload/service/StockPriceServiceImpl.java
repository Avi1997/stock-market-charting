package com.cognizant.stockmarket.excelupload.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.stockmarket.excelupload.model.StockPrice;
import com.cognizant.stockmarket.excelupload.repository.StockPriceRepository;

@Service
public class StockPriceServiceImpl implements StockPriceService {

	@Autowired
	StockPriceRepository stockPriceRepository;

	@Override
	@Transactional
	public List<StockPrice> getAllStrockPrice(long companyCode) {
		return stockPriceRepository.findByCompanyCode(companyCode);
	}
	
	@Transactional
	public List<StockPrice> getStockExchangesList(Long companyCode) {
		return stockPriceRepository.getStockExchangeList(companyCode);
	}

}
