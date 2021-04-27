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
import com.cts.customerregistration.exceptions.CustomerNotFoundException;
import com.cts.customerregistration.model.Customer;


@SpringBootTest
class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Mock
	private CustomerDao customerDao;

	private String pan;
	private Optional<Customer> customer;

	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		pan = "QWERT1234Y";
		customer = Optional.ofNullable(new Customer("QWERT1234Y", "Gandhi Street, New Delhi - 07",
				Date.valueOf("1984-04-25"), "MALE", "Vishvajeet Sameul", "password"));
	}

	@Test
	void getCustomerByPanTest() throws CustomerNotFoundException {
		when(customerDao.findById(pan)).thenReturn(customer);
		Assertions.assertEquals(customer.get(), customerService.getCustomerByPan(pan));
	}
	
	@Test
	void getCustomerByPanExceptionTest() throws CustomerNotFoundException {
		when(customerDao.findById(pan)).thenReturn(Optional.empty());
		Assertions.assertThrows(CustomerNotFoundException.class,() -> customerService.getCustomerByPan(pan));
	}

}
