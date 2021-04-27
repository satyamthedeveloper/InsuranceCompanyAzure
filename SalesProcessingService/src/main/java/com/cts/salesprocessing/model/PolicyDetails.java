package com.cts.salesprocessing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDetails {

	@Id
	String name;
	int maturityAge;
	int entryAgeStart;
	int entryAgeEnd;
	int premiumPeriod;
	double insuranceFactor;
}
