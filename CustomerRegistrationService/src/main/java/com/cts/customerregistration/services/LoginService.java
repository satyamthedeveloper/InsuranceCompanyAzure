package com.cts.customerregistration.services;

import java.sql.Date;

public interface LoginService {

	String verifyCustomer(String pAN, Date formattedDate, String password);

}
