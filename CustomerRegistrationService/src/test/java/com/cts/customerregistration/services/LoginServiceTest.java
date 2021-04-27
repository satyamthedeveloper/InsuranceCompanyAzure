package com.cts.customerregistration.services;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.customerregistration.dao.CustomerDao;
import com.cts.customerregistration.model.Customer;

@SpringBootTest
class LoginServiceTest {

	@InjectMocks
	private LoginServiceImpl loginServiceImpl;
	
	@Mock
	private CustomerDao customerDao;
	
	private String pAN;
	private Optional<Customer> customer;
	
	@BeforeEach
	private void init() {
		MockitoAnnotations.initMocks(this);
		pAN = "QWERT1234Y";
		customer = Optional.ofNullable(new Customer("QWERT1234Y", "Gandhi Street, New Delhi - 07",
				Date.valueOf("1984-04-25"), "MALE", "Vishvajeet Sameul", "password"));
		when(customerDao.findById(pAN)).thenReturn(customer);
	}
	
	@Test
	void verifyCustomerTest() {
		
		Assertions.assertEquals("Login Successful.", loginServiceImpl.verifyCustomer(pAN, Date.valueOf("1984-04-25"), "password"));
	}
	
	@Test
	void verifyCustomerFailTest1() {
		Assertions.assertEquals("Invalid Credentials", loginServiceImpl.verifyCustomer(pAN, Date.valueOf("1984-04-25"), "pass"));
	}
	
	@Test
	void verifyCustomerFailTest2() {
		Assertions.assertEquals("Invalid Credentials", loginServiceImpl.verifyCustomer(pAN, Date.valueOf("1984-04-21"), "password"));
	}
	
	@Test
	void verifyCustomerFailTest3() {
		when(customerDao.findById(pAN)).thenReturn(Optional.empty());
		Assertions.assertEquals("No Such User Exist, Register First.", loginServiceImpl.verifyCustomer(pAN, Date.valueOf("1984-04-21"), "password"));
	}
}
