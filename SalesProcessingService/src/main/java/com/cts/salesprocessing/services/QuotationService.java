package com.cts.salesprocessing.services;

import com.cts.salesprocessing.exception.PolicyNotFoundException;
import com.cts.salesprocessing.exception.QuotationNotFoundException;
import com.cts.salesprocessing.model.CustomerDisplay;
import com.cts.salesprocessing.model.Quotation;

public interface QuotationService {

	Quotation getQuotation(String policyName, String pAN) throws PolicyNotFoundException;

	String saveQuotation(Quotation quotation);

	Quotation retriveQuotation(int id) throws QuotationNotFoundException;

	String submitApplication(Quotation quote) ;

	CustomerDisplay getCustomer(String pan);

}
