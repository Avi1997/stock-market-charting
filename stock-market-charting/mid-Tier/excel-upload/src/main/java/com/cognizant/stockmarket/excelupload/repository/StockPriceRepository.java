package com.cognizant.stockmarket.excelupload.repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.stockmarket.excelupload.model.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Integer> {
	
	@Query(value="select min(sp_date) from stock_price",nativeQuery=true)
	public Date minDate();
	
	@Query(value="select max(sp_date) from stock_price",nativeQuery=true)
	public Date maxDate();
	
	@Query(value="select * from stock_price where sp_date=?1 and sp_time=?2 and sp_code=?3 and sp_stock_exchange=?4 LIMIT 1",nativeQuery=true)
	public StockPrice getStock(Date date,Time time,long code,String exchange);
	
	@Query(value="select * from stock_price where sp_code=?1 group by sp_date",nativeQuery = true)
	public List<StockPrice> findByCompanyCode(long companyCode);	
	
	@Query(value="  select a.* from stock_price a inner join (\r\n" + 
			" select sp_id,sp_stock_exchange,max(sp_date) as max_date,max(sp_time) as max_time from stock_price where sp_code=?1 group by sp_stock_exchange) b\r\n" + 
			" on a.sp_id=b.sp_id;",nativeQuery = true)
	public List<StockPrice> getStockExchangeList(Long companyCode);
	
}
