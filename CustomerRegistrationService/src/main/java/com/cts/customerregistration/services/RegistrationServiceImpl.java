package com.cts.customerregistration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.customerregistration.dao.CustomerDao;
import com.cts.customerregistration.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public String register(Customer customer) {
		// TODO Auto-generated method stub
		log.debug("Received: " + customer);

		customerDao.save(customer);

		log.debug(customer + ": registeration successful");
		return "Customer Registered.";
	}
}
