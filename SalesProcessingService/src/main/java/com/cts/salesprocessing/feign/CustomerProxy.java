package com.cts.salesprocessing.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cts.salesprocessing.model.Customer;


@FeignClient(value="CustomerReg",url="${customer.registration.service}")
public interface CustomerProxy {
	
	@GetMapping("/customer/{pan}")
	public ResponseEntity<Customer> getCustomerByPan(@PathVariable("pan") String pan);
}
