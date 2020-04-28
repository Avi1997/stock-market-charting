package com.cognizant.stockmarket.excelupload.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cognizant.stockmarket.excelupload.dto.ExcelUploadDTO;
import com.cognizant.stockmarket.excelupload.service.ExcelUploadService;

@RestController
@RequestMapping("/stock-market")
public class FileUploadController {

	@Autowired
	ExcelUploadService excelUploadService;

	@PostMapping("/upload-stock-data")
	public void uploadStockData(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
		String fileName = file.getOriginalFilename();
		File dir = new File("C:\\Users\\Admin\\Documents");
		if (dir.isDirectory()) {
			File serverFile = new File(dir, fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(file.getBytes());
			stream.close();
		}
		excelUploadService.uploadFileService(dir + "\\" + fileName);
	}

	@GetMapping("/summary")
	public ExcelUploadDTO getUploadedData() {
		return excelUploadService.getSummary();
	}

}
