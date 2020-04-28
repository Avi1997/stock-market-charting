package com.cognizant.stockmarket.excelupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.stockmarket.excelupload.model.Company;



public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	public Company findByCompanyCode(Long companyCode);
	
}
