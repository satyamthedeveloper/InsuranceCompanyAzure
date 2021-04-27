package com.cts.customerregistration.services;

import com.cts.customerregistration.exceptions.CustomerNotFoundException;
import com.cts.customerregistration.model.Customer;

public interface CustomerService {

	Customer getCustomerByPan(String pan) throws CustomerNotFoundException;

}
