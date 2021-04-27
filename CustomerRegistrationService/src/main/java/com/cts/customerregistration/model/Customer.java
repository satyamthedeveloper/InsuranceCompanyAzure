package com.cts.customerregistration.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	private String pan;
	private String name;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String gender;
	private String address;
	private String password;

}
