package com.cognizant.stockmarketcharting.excelupload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.stockmarket.excelupload.model.StockPrice;
import com.cognizant.stockmarket.excelupload.repository.StockPriceRepository;
import com.cognizant.stockmarket.excelupload.service.StockPriceService;

@SpringBootTest
class ExcelUploadApplicationTests {
	@MockBean
	StockPriceRepository stockPriceRepository;

	@Autowired
	StockPriceService stockPriceService;

	public StockPrice createStockPrice() {
		StockPrice stockPrice = new StockPrice(1, 500112L, "BSE", 345L, new Date(), new Time(0));
		return stockPrice;
	}

	@Test
	public void testGetAllStockPrice() {
		when(stockPriceRepository.findByCompanyCode(500112L)).thenReturn(createStockPriceList());
		List<StockPrice> stockPrices = stockPriceService.getAllStrockPrice(500112L);
		assertEquals(stockPrices.get(0).getCurrentPrice(), createStockPriceList().get(0).getCurrentPrice());
	}

	public List<StockPrice> createStockPriceList() {
		List<StockPrice> stockPrices = new ArrayList<StockPrice>();
		stockPrices.add(createStockPrice());
		return stockPrices;
	}

}
