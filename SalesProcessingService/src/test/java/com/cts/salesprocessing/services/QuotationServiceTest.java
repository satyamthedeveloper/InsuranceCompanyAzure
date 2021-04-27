package com.cts.salesprocessing.services;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.salesprocessing.dao.PolicyDetailsDao;
import com.cts.salesprocessing.dao.QuotationDao;
import com.cts.salesprocessing.exception.PolicyNotFoundException;
import com.cts.salesprocessing.exception.QuotationNotFoundException;
import com.cts.salesprocessing.feign.CustomerProxy;
import com.cts.salesprocessing.model.Customer;
import com.cts.salesprocessing.model.CustomerDisplay;
import com.cts.salesprocessing.model.PolicyDetails;
import com.cts.salesprocessing.model.Quotation;

@SpringBootTest
public class QuotationServiceTest {

	@InjectMocks
	private QuotationServiceImpl service;

	@Mock
	private PolicyDetailsDao policyDao;

	@Mock
	private QuotationDao quotationDao;

	@Mock
	private CustomerProxy customerProxy;

	private String pan;
	private ResponseEntity<Customer> customer;
	private Quotation quotation;
	private Optional<PolicyDetails> policy;
	
	@BeforeEach
	private void init() {
		MockitoAnnotations.initMocks(this);
		pan = "QWERT1234Y";
		customer = new ResponseEntity<Customer>(new Customer("QWERT1234Y", "Vishvajeet Sameul",
				Date.valueOf("1984-04-25"), "MALE", "Gandhi Street, New Delhi - 07", "password"), HttpStatus.OK);
		when(customerProxy.getCustomerByPan(pan)).thenReturn(customer);
		quotation = new Quotation(0, pan, "life_insurance", 446.42857142857144, 375000.0, false);
		policy = Optional.of(new PolicyDetails("life_insurance", 55, 21, 5, 70, 15));
		when(policyDao.findById("life_insurance")).thenReturn(policy);
		when(quotationDao.findById(0)).thenReturn(Optional.of(quotation));
	}

	@Test
	void getCustomerTest() {
		Assertions.assertEquals(new CustomerDisplay("Vishvajeet Sameul", "MALE"), service.getCustomer("QWERT1234Y"));
	}

	@Test
	void getQuotationTest() throws PolicyNotFoundException {
		Assertions.assertEquals(quotation, service.getQuotation("life_insurance", pan));
	}
	
	@Test
	void getQuotationExceptionTest() throws PolicyNotFoundException {
		Assertions.assertThrows(PolicyNotFoundException.class,() -> service.getQuotation("life_insuranc", pan));
	}
	
	@Test
	void saveQuotationTest() {
		Assertions.assertEquals("Quotation Saved.", service.saveQuotation(quotation));
	}
	
	
	@Test
	void submitApplicationTest() {
		Assertions.assertEquals("Form submitted", service.submitApplication(quotation));
	}
	
	@Test
	void  retriveQuotationTest() throws QuotationNotFoundException {
		Assertions.assertEquals(quotation, service.retriveQuotation(0));
	}
	
	@Test
	void  retriveExceptionQuotationTest() throws QuotationNotFoundException {
		Assertions.assertThrows(QuotationNotFoundException.class, () -> service.retriveQuotation(8));
	}
}
