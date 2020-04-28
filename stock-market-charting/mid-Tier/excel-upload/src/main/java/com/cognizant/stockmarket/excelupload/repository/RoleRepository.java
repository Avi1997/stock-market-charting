package com.cognizant.stockmarket.excelupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.stockmarket.excelupload.model.Role;




@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>   {

	public Role findByName(String name);
}