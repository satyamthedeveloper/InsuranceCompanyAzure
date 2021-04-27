package com.cts.customerregistration.services;

import java.sql.Date;
import java.util.Optional;
import java.util.function.BiPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.customerregistration.dao.CustomerDao;
import com.cts.customerregistration.model.Customer;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerDao customerDao;

	BiPredicate<Customer, Date> compareDate = (customer, date) -> customer.getDateOfBirth().compareTo(date) == 0;

	BiPredicate<Customer, String> comparePassword = (customer, pass) -> customer.getPassword().equals(pass);

	@Override
	public String verifyCustomer(String pAN, Date formattedDate, String password) {

		log.debug("Details received : " + pAN + ": " + formattedDate);

		Optional<Customer> dbCustomer = customerDao.findById(pAN);

		log.debug("databse checked");
		if (dbCustomer.isEmpty())
			return "No Such User Exist, Register First.";

		log.debug("entry found");
		log.debug("compare date: " + comparePassword.test(dbCustomer.get(), password));
		if (compareDate.test(dbCustomer.get(), formattedDate) && comparePassword.test(dbCustomer.get(), password)) {

			return "Login Successful.";
		}

		return "Invalid Credentials";
	}

}
