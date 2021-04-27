package com.cts.customerregistration.services;

import java.sql.Date;
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
class RegistrationServiceTest {

	@InjectMocks
	private RegistrationServiceImpl registrationService;
	
	@Mock
	private CustomerDao customerDao;
	
	private Customer customer;
	
	@BeforeEach
	private void init() {
		MockitoAnnotations.initMocks(this);
		customer = new Customer("QWERT1234Y", "Gandhi Street, New Delhi - 07",
				Date.valueOf("1984-04-25"), "MALE", "Vishvajeet Sameul", "password");
	}
	
	@Test
	void registerTest() {
		Assertions.assertEquals("Customer Registered.", registrationService.register(customer));
	}
	
//	@Test
//	public void registerFailTest() {
//		Assertions.assertEquals(false, registrationService.register(null));
//	}
}
