package com.cts.customerregistration.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.customerregistration.dao.CustomerDao;
import com.cts.customerregistration.exceptions.CustomerNotFoundException;
import com.cts.customerregistration.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public Customer getCustomerByPan(String pan) throws CustomerNotFoundException {

		log.debug("Service IN");
		Optional<Customer> resultCustomer = customerDao.findById(pan);

		if (resultCustomer.isEmpty()) {
			log.debug("Customer found is null");
			throw new CustomerNotFoundException(pan + " :is invalid input.");
		}

		return resultCustomer.get();
	}

}
