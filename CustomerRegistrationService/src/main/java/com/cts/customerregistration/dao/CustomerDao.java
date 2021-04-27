package com.cts.customerregistration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.customerregistration.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, String>{

}
