package com.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.customer.entity.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long>{

	@Query("SELECT c FROM CustomerAddress c WHERE c.customerEntity.id = :id")
	public List<CustomerAddress> findAddressbyCustomerId(Long id);

}
