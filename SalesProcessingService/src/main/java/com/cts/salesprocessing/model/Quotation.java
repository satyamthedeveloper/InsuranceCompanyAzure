package com.cts.salesprocessing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"pan"}))
public class Quotation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int qid;
	
	@Column(name = "pan")
	String pAN;
	String policyName;
	double premium;
	double amountInsured;
	boolean submitted;

}
