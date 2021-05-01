package com.cts.customerregistration.controller;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customerregistration.exceptions.CustomerNotFoundException;
import com.cts.customerregistration.model.Customer;
import com.cts.customerregistration.model.LoginInput;
import com.cts.customerregistration.services.CustomerService;
import com.cts.customerregistration.services.LoginService;
import com.cts.customerregistration.services.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/customer")
public class CustomerController {

	@Autowired
	private RegistrationService registration;
	
	@Autowired
	private LoginService login;	
	
	@Autowired
	private CustomerService customerService;

	@PostMapping("/register")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
		log.debug("Controller In");
		log.debug(customer.toString());
		String result = registration.register(customer);
		log.debug("Controller Out");
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> loginCustomer(@RequestBody LoginInput loginInput) {
		log.debug("Controller In");
		log.debug(loginInput.toString());
		Date formattedDate = Date.valueOf(loginInput.getDate());
		String result = login.verifyCustomer(loginInput.getPan(), formattedDate, loginInput.getPassword());
		log.debug("output: "+result);
		log.debug("Controller Out");
		if(result.equals("Login Successful.")) return new ResponseEntity<String>(result, HttpStatus.OK);
		return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/{pan}")
//	@Cacheable(value = "customer", key = "#pan")
	public ResponseEntity<Customer> getCustomerByPan(@PathVariable("pan") String pan) throws CustomerNotFoundException{
		log.debug("Controller In");
		Customer resultCustomer = customerService.getCustomerByPan(pan);
		log.debug("Controller Out");
		return new ResponseEntity<Customer>(resultCustomer, HttpStatus.OK);
	}

}
