package com.cognizant.stockmarket.companyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.stockmarket.companyservice.model.User;





@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}