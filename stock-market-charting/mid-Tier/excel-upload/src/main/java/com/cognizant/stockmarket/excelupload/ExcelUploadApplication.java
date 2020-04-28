package com.cognizant.stockmarket.excelupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ExcelUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelUploadApplication.class, args);
	}

}
