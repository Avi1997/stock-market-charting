package com.cognizant.stockmarket.companyservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.stockmarket.companyservice.model.Company;
import com.cognizant.stockmarket.companyservice.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}

	@Override
	@Transactional
	public List<Company> findAllCompanies() {
		// TODO Auto-generated method stub
		return companyRepository.findAll();
	}

}
