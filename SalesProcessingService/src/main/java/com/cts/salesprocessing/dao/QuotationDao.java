package com.cts.salesprocessing.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.salesprocessing.model.Quotation;

@Repository
@Transactional
public interface QuotationDao extends JpaRepository<Quotation, Integer>{

	@Query(nativeQuery = true, value = "Select * from quotation q where q.pan = :pAN")
	Optional<Quotation> findByPAN(@Param("pAN") String pAN);

}
