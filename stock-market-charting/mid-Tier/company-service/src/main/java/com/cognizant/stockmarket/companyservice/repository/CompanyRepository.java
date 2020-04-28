package com.cognizant.stockmarket.companyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.stockmarket.companyservice.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	}
