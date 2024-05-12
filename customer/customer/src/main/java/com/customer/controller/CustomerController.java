package com.customer.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.constants.CustomerConstants;
import com.customer.constants.CustomerUrlConstants;
import com.customer.finalResponse.FinalResponse;
import com.customer.request.CustomerAddressRequest;
import com.customer.request.CustomerRequest;
import com.customer.response.CustomerAddressResponse;
import com.customer.response.CustomerResponse;
import com.customer.response.CustomerWithAddressResponse;
import com.customer.service.CustomerService;
import com.customer.validation.CustomerValidation;

@RestController
@RequestMapping(CustomerUrlConstants.BASE_URL)
public class CustomerController {
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	//===================================================================================

	//=====================Save Customer==========================
	@PostMapping(CustomerUrlConstants.CUSTOMER_SAVE)
	public FinalResponse saveCustomer(@RequestBody() CustomerRequest customerRequest) {
		String methodStart = "Start Method";
		logger.info(methodStart + " saveCustomer");
		
		FinalResponse finalResponse = null;
		CustomerResponse customerResponse = null;
		
		String Validation = CustomerValidation.isValidatioSuccess(customerRequest);
		if(!Validation.equalsIgnoreCase(CustomerConstants.VALIDATION_SUCCESS)) {
			return new FinalResponse(Validation, null, null);
		}
		
		customerResponse = customerService.saveCustomer(customerRequest);
		if(customerResponse != null) {
			finalResponse = new FinalResponse("Customer Saved Successfully", HttpStatus.CREATED, customerResponse);
		}
		else {
			finalResponse = new FinalResponse("Unable To Save Customer", HttpStatus.NOT_FOUND, customerResponse);
		}
		
		String endMethod = "End Method";
		logger.info(endMethod + " saveCustomer");
		return finalResponse;
	}
	
	//=======================Get Customer=======================
	@GetMapping(CustomerUrlConstants.CUSTOMER_GET)
	public FinalResponse getCustomerById(@PathVariable() Long id) {
		String methodStart = "Start Method";
		logger.info(methodStart + " getCustomerById");
		
		FinalResponse finalResponse = null;
		CustomerResponse customerResponse = null;
		customerResponse = customerService.getCustomerById(id);
		if(customerResponse != null) {
			finalResponse = new FinalResponse("Customer Retrive Successfully", HttpStatus.OK, customerResponse);
		}
		else {
			finalResponse = new FinalResponse("No Data Found", HttpStatus.NOT_FOUND, customerResponse);
		}
		
		String endMethod = "End Method";
		logger.info(endMethod + " getCustomerById");
		return finalResponse;
	}
	
	//=====================Save Customer Address==========================
	
	@PostMapping(CustomerUrlConstants.CUSTOMER_ADDRESS_SAVE)
	public FinalResponse saveCustomerAddress(@RequestBody() List<CustomerAddressRequest> customerAddressRequest) {
		String methodStart = "Start Method";
		logger.info(methodStart + " saveCustomerAddress");
		
		FinalResponse finalResponse = null;
		List<CustomerAddressResponse> customerAddress = null;
		
		String Validation = CustomerValidation.isValidatioSuccess(customerAddressRequest);
		if(!Validation.equalsIgnoreCase(CustomerConstants.VALIDATION_SUCCESS)) {
			return new FinalResponse(Validation, null, null);
		}
		
		customerAddress = customerService.saveCustomerAddress(customerAddressRequest);
		if(customerAddress != null) {
			finalResponse = new FinalResponse("Customer Address Saved Successfully", HttpStatus.CREATED, customerAddress);
		}
		else {
			finalResponse = new FinalResponse("Unable To Save Customer Address", HttpStatus.NOT_FOUND, customerAddress);
		}
		
		String endMethod = "End Method";
		logger.info(endMethod + " saveCustomerAddress");
		return finalResponse;
	}
		
		//=====================Get Customer With Address==========================
		
	@GetMapping(CustomerUrlConstants.CUSTOMER_ADDRESS_GET_BY_CUSTOMERID)
	public FinalResponse getCustomerAddressByCustomer(@RequestParam() Long id) {
		String methodStart = "Start Method";
		logger.info(methodStart + " getCustomerAddressByCustomer");
		
		FinalResponse finalResponse = null;
		List<CustomerAddressResponse> customerAddress = null;
		
		customerAddress = customerService.getCustomerAddressByCustomer(id);
		if(customerAddress != null) {
			finalResponse = new FinalResponse("Customer Address Get Successfully", HttpStatus.CREATED, customerAddress);
		}
		else {
			finalResponse = new FinalResponse("Unable To Get Customer Address", HttpStatus.NOT_FOUND, customerAddress);
		}
		
		String endMethod = "End Method";
		logger.info(endMethod + " getCustomerAddressByCustomer");
		return finalResponse;
	}
	
	//=====================Get Customer With Address==========================
	
	@GetMapping(CustomerUrlConstants.GET_CUSTOMER_DETAILS_WITH_ADDRESS_BY_CUSTOMERID)
	public FinalResponse getCustomerWithAddress(@RequestParam() Long id) {
		String methodStart = "Start Method";
		logger.info(methodStart + " getCustomerAddressByCustomer");
		
		FinalResponse finalResponse = null;
		CustomerWithAddressResponse customerAddress = null;
		
		customerAddress = customerService.getCustomerWithAddress(id);
		if(customerAddress != null) {
			finalResponse = new FinalResponse("Customer With Address Get Successfully", HttpStatus.CREATED, customerAddress);
		}
		else {
			finalResponse = new FinalResponse("Unable To Get Customer And Address", HttpStatus.NOT_FOUND, customerAddress);
		}
		
		String endMethod = "End Method";
		logger.info(endMethod + " getCustomerAddressByCustomer");
		return finalResponse;
	}
}
