package com.cognizant.stockmarket.excelupload.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.stockmarket.excelupload.dto.ExcelUploadDTO;
import com.cognizant.stockmarket.excelupload.exception.InvalidFormatException;
import com.cognizant.stockmarket.excelupload.model.StockPrice;
import com.cognizant.stockmarket.excelupload.repository.CompanyRepository;
import com.cognizant.stockmarket.excelupload.repository.StockPriceRepository;

@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {

	@Autowired
	StockPriceRepository stockPriceRepository;
	Long companyCodeNew;
	@Autowired
	CompanyRepository companyRepository;

	ExcelUploadDTO excelUploadDTO = new ExcelUploadDTO();

	@Override
	@Transactional
	public void uploadFileService(String filePath) throws FileNotFoundException {

		Date minDate = null;
		Date maxDate = null;
		FileInputStream inputStream = new FileInputStream(filePath);

		int count = 0;
		Workbook workbook;
		try {
			workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = firstSheet.iterator();
			rowIterator.next();

			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				StockPrice stockPrice = new StockPrice();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						Long companyCode = (long) nextCell.getNumericCellValue();
						stockPrice.setCompanyCode(companyCode);
						companyCodeNew = companyCode;
						break;
					case 1:
						String stockExchange = nextCell.getStringCellValue();
						stockPrice.setStockExchange(stockExchange);
						excelUploadDTO.setStockExchange(stockExchange);
						break;
					case 2:
						Long currentPrice = (long) nextCell.getNumericCellValue();
						stockPrice.setCurrentPrice(currentPrice);
						break;
					case 3:
						Date date = nextCell.getDateCellValue();
						if (minDate == null) {
							minDate = date;
						}
						if (maxDate == null) {
							maxDate = date;
						}
						if (date.compareTo(minDate) < 0) {
							minDate = date;

						}
						if (date.compareTo(maxDate) > 0) {
							maxDate = date;

						}
						stockPrice.setDate(date);
						break;
					case 4:
						Date time = nextCell.getDateCellValue();
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
						stockPrice.setTime(Time.valueOf(sdf.format(time)));
						break;
					default:
						break;
					}
				}
				if (stockPrice.getCompanyCode() != null) {
					StockPrice newStockPrice = stockPriceRepository.getStock(stockPrice.getDate(), stockPrice.getTime(),
							stockPrice.getCompanyCode(), stockPrice.getStockExchange());
					if (newStockPrice == null) {
						count = count + 1;
						stockPriceRepository.save(stockPrice);
					}
				}
			}
			workbook.close();
		} catch (Exception e) {
			throw new InvalidFormatException();
		}
		if (count != 0) {
			count--;
		}
		excelUploadDTO.setNoOfRecords(count);
		excelUploadDTO.setCompanyName(companyRepository.findByCompanyCode(companyCodeNew).getName());
		excelUploadDTO.setMaxDate(maxDate);
		excelUploadDTO.setMinDate(minDate);
	}

	@Override
	@Transactional
	public ExcelUploadDTO getSummary() {
		return excelUploadDTO;
	}

}
