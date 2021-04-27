package com.cts.salesprocessing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.salesprocessing.model.PolicyDetails;

@Repository
public interface PolicyDetailsDao extends JpaRepository<PolicyDetails, String>{

}
