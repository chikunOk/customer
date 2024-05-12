package com.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.entity.CustomerAddress;
import com.customer.entity.CustomerEntity;
import com.customer.repository.CustomerAddressRepository;
import com.customer.repository.CustomerRepository;
import com.customer.request.CustomerAddressRequest;
import com.customer.request.CustomerRequest;
import com.customer.response.CustomerAddressResponse;
import com.customer.response.CustomerResponse;
import com.customer.response.CustomerWithAddressResponse;

@Service
public class CustomerService {
	
	private static final Logger logger = LogManager.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerAddressRepository customerAddressRepository;
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
	
	//=========================Save Customer Address=========================

	public List<CustomerAddressResponse> saveCustomerAddress(List<CustomerAddressRequest> customerAddressRequest) {
		String methodStart = "Start Method";
		logger.info(methodStart + " saveCustomerAddress");
		
		List<CustomerAddressResponse> customerAddressList = null;
		CustomerAddressResponse customerAddress = null;
		List<CustomerAddress> customerAddList = null;
		CustomerAddress customerAdd = null;
		
		if(customerAddressRequest != null && !customerAddressRequest.isEmpty()) {
			customerAddList = new ArrayList<>();
			for(CustomerAddressRequest customer : customerAddressRequest) {
				customerAdd = new CustomerAddress();
				
				CustomerEntity customerEntity = new CustomerEntity();
				customerEntity.setId(customer.getCustomerId());
				
				customerAdd.setCountry(customer.getCountry());
				customerAdd.setState(customer.getState());
				customerAdd.setDistrict(customer.getDistrict());
				customerAdd.setCity(customer.getCity());
				customerAdd.setPoliceStation(customer.getPoliceStation());
				customerAdd.setPostOffice(customer.getPostOffice());
				customerAdd.setPinCode(customer.getPinCode());
				customerAdd.setCustomerEntity(customerEntity);
				
				customerAddList.add(customerAdd);
			}
			customerAddList = customerAddressRepository.saveAll(customerAddList);
		}
		
		if(customerAddList != null && !customerAddList.isEmpty()) {
			customerAddressList = new ArrayList<>();
			for(CustomerAddress address : customerAddList) {
				customerAddress = new CustomerAddressResponse();
				
				customerAddress.setCountry(address.getCountry());
				customerAddress.setState(address.getState());
				customerAddress.setDistrict(address.getDistrict());
				customerAddress.setCity(address.getCity());
				customerAddress.setPoliceStation(address.getPoliceStation());
				customerAddress.setPostOffice(address.getPostOffice());
				customerAddress.setPinCode(address.getPinCode());
				customerAddressList.add(customerAddress);
			}
		}
		
		String endMethod = "End Method";
		logger.info(endMethod + " saveCustomerAddress");
		return customerAddressList;
	}
	
	//=========================Get Customer Address By Customer Id=========================

	public List<CustomerAddressResponse> getCustomerAddressByCustomer(Long id) {
		String methodStart = "Start Method";
		logger.info(methodStart + " getCustomerAddressByCustomer");
		
		List<CustomerAddress> customerAddList = null;
		List<CustomerAddressResponse> customerAddressList = null;
		CustomerAddressResponse customerAddress = null;
		
		if(id != null) {
			customerAddList = customerAddressRepository.findAddressbyCustomerId(id);
		}
		
		if(customerAddList != null) {
			customerAddressList = new ArrayList<>();
			for(CustomerAddress address : customerAddList) {
				customerAddress = new CustomerAddressResponse();
				customerAddress.setCountry(address.getCountry());
				customerAddress.setState(address.getState());
				customerAddress.setDistrict(address.getDistrict());
				customerAddress.setCity(address.getCity());
				customerAddress.setPoliceStation(address.getPoliceStation());
				customerAddress.setPostOffice(address.getPostOffice());
				customerAddress.setPinCode(address.getPinCode());
				customerAddressList.add(customerAddress);
			}
		}
		
		String endMethod = "End Method";
		logger.info(endMethod + " getCustomerAddressByCustomer");
		return customerAddressList;
	}

	//=========================Get Customer With Address By Customer Id=========================
	
	public CustomerWithAddressResponse getCustomerWithAddress(Long id) {
		String methodStart = "Start Method";
		logger.info(methodStart + " getCustomerWithAddress");
		
		CustomerWithAddressResponse customerWithAddress = null;
		CustomerResponse customerResponse = null;
		List<CustomerAddressResponse> customerAddressresponseList = null;
		CustomerAddressResponse customerAddressResponse = null;
		CustomerEntity customerEntity = null;
		List<CustomerAddress> customerAddress = null;
		
		if(id != null) {
			customerEntity = customerRepository.findById(id).orElse(null);
			customerAddress = customerAddressRepository.findAddressbyCustomerId(id);
		}
		
		if(customerEntity != null && (customerAddress != null && !customerAddress.isEmpty())) {
			customerResponse = new CustomerResponse();
			customerAddressresponseList = new ArrayList<>();
			customerWithAddress = new CustomerWithAddressResponse();
			
			BeanUtils.copyProperties(customerEntity, customerResponse);
			for(CustomerAddress address : customerAddress) {
				customerAddressResponse = new CustomerAddressResponse();
				
				customerAddressResponse.setCountry(address.getCountry());
				customerAddressResponse.setState(address.getState());
				customerAddressResponse.setDistrict(address.getDistrict());
				customerAddressResponse.setCity(address.getCity());
				customerAddressResponse.setPoliceStation(address.getPoliceStation());
				customerAddressResponse.setPostOffice(address.getPostOffice());
				customerAddressResponse.setPinCode(address.getPinCode());
				customerAddressresponseList.add(customerAddressResponse);
			}
			customerWithAddress.setCustomerResponse(customerResponse);
			customerWithAddress.setCustomerAddressResponses(customerAddressresponseList);
		}
		
		String endMethod = "End Method";
		logger.info(endMethod + " getCustomerWithAddress");
		return customerWithAddress;
	}

}
