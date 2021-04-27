package com.cts.salesprocessing.model;

import java.sql.Date;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

	public enum Gender {
		MALE, FEMALE
	}

	@Id
	private String pAN;
	private String name;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String gender;
	private String address;
	private String password;

}