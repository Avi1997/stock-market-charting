package com.cognizant.stockmarket.companyservice.service;

import java.util.List;

import com.cognizant.stockmarket.companyservice.model.Company;

public interface CompanyService {
	public List<Company> findAllCompanies();
}
