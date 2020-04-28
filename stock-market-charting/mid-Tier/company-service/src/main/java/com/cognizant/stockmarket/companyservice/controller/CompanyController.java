package com.cognizant.stockmarket.companyservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.stockmarket.companyservice.model.Company;
import com.cognizant.stockmarket.companyservice.service.CompanyService;


@RestController
@RequestMapping("/stock-market")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping("/companies")
	public List<Company> getAllCompanies(){
		return companyService.findAllCompanies();
	}
	
}
