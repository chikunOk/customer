package com.customer.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entity.CustomerEntity;
import com.customer.repository.CustomerRepository;
import com.customer.request.CustomerRequest;
import com.customer.response.CustomerResponse;

@Service
public class CustomerService {
	
	private static final Logger logger = LogManager.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//==========================================================================

	//=======================Save Customer===========================
	
	public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
		String methodStart = "Start Method";
		logger.info(methodStart + " saveCustomer");
		
		CustomerEntity customerEntity = null;
		CustomerResponse customerResponse = null;
		
		if(customerRequest != null) {
			customerEntity = new CustomerEntity();
			BeanUtils.copyProperties(customerRequest, customerEntity);
		}
		customerEntity = customerRepository.save(customerEntity);
		if(customerEntity != null) {
			customerResponse = new CustomerResponse();
			BeanUtils.copyProperties(customerEntity, customerResponse);
		}
		
		String endMethod = "End Method";
		logger.info(endMethod + " saveCustomer");
		return customerResponse;
	}

	//=========================Get Customer=========================
	public CustomerResponse getCustomerById(Long id) {
		String methodStart = "Start Method";
		logger.info(methodStart + " getCustomerById");
		
		CustomerEntity customerEntity = null;
		CustomerResponse customerResponse = null;
		if(id != null) {
			customerEntity  = customerRepository.findById(id).orElse(null);
		}
		if(customerEntity != null) {
			customerResponse = new CustomerResponse();
			BeanUtils.copyProperties(customerEntity, customerResponse);
		}
		
		String endMethod = "End Method";
		logger.info(endMethod + " getCustomerById");
		return customerResponse;
	}

}
