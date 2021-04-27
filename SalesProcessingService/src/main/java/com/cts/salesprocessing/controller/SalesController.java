package com.cts.salesprocessing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.salesprocessing.exception.PolicyNotFoundException;
import com.cts.salesprocessing.exception.QuotationNotFoundException;
import com.cts.salesprocessing.model.CustomerDisplay;
import com.cts.salesprocessing.model.IdInput;
import com.cts.salesprocessing.model.InputFormat;
import com.cts.salesprocessing.model.Quotation;
import com.cts.salesprocessing.services.QuotationService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	private QuotationService quotation;

	@CrossOrigin(origins = "http://localhost:3000")
	@HystrixCommand(fallbackMethod = "getQuoteFallback")
	@PostMapping("/getQuote")
	@Cacheable(value = "sales", key = "#inputFormat.getPan()")
	public ResponseEntity<Quotation> getQuote(@RequestBody InputFormat inputFormat) throws PolicyNotFoundException {

		log.debug("Controller IN");
		log.debug("pan:" + inputFormat.getPan() + ":" + inputFormat.getPolicyName());
		Quotation quote = quotation.getQuotation(inputFormat.getPolicyName(), inputFormat.getPan());
		log.debug("Controller OUT");
		return ResponseEntity.ok(quote);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/saveQuote")
	@CachePut(value = "sales", key = "#quote")
	public ResponseEntity<String> saveQuote(@RequestBody Quotation quote) {
		log.debug("Controller IN");
		String result = quotation.saveQuotation(quote);
		log.debug("Controller OUT");
		return ResponseEntity.ok(result);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path = "/retriveQuote")
//	@Cacheable(value = "sales", key = "#id")
	public ResponseEntity<Quotation> retriveQuote(@RequestBody IdInput id) throws QuotationNotFoundException {
		log.debug("Controller IN");
		log.debug(id.getId() + "");
		Quotation quote = quotation.retriveQuotation(id.getId());
		log.debug("Controller OUT");
		return ResponseEntity.ok(quote);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/submit")
	@CachePut(value = "salesSubmit", key = "#quote")
	public ResponseEntity<String> submitApplication(@RequestBody Quotation quote) {
		log.debug("Controller IN");
		String result = quotation.submitApplication(quote);
		log.debug("Controller OUT");
		return ResponseEntity.ok(result);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@HystrixCommand(fallbackMethod = "getCustomerFallback")
	@PostMapping("/getCustomer")
	@Cacheable(value = "customer", key = "#inputFormat.getPan()")
	public ResponseEntity<CustomerDisplay> getCustomer(@RequestBody InputFormat inputFormat) {

		log.debug("Controller IN");
		log.debug("pan:" + inputFormat.getPan() + ":" + inputFormat.getPolicyName());
		CustomerDisplay details = quotation.getCustomer(inputFormat.getPan());
		log.debug("Controller OUT");
		return ResponseEntity.ok(details);
	}

	public ResponseEntity<Quotation> getQuoteFallback(@RequestBody InputFormat inputFormat)
			throws PolicyNotFoundException {
		log.debug("Fallback IN");
		Quotation quote = new Quotation(0, "AAAAA0000A", "Default Insurance", 0.0, 0, false);
		log.debug("Fallback OUT");
		return ResponseEntity.ok(quote);
	}

	public ResponseEntity<CustomerDisplay> getCustomerFallback(@RequestBody InputFormat inputFormat) {
		log.debug("Fallback IN");
		CustomerDisplay details = new CustomerDisplay("Service Down", "Gender");
		log.debug("Fallback OUT");
		return ResponseEntity.ok(details);
	}
}
