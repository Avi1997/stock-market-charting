package com.cognizant.stockmarket.excelupload.service;

import java.io.FileNotFoundException;

import com.cognizant.stockmarket.excelupload.dto.ExcelUploadDTO;


public interface ExcelUploadService {
	public void uploadFileService(String filePath) throws FileNotFoundException;
	public ExcelUploadDTO getSummary();
}
