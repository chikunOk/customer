package com.customer.validation;

import org.springframework.stereotype.Component;

import com.customer.constants.CustomerConstants;
import com.customer.request.CustomerRequest;

@Component
public class CustomerValidation {

	public static String ifValidate(String Message, String object) {
		if(object != null && !object.trim().isEmpty()) {
			return CustomerConstants.VALIDATION_SUCCESS;
		}
		else {
			return Message + " Can Not Be Null";
		}
	}
	
	public static String ifValidate(String Message, Long id) {
		if(id != null && id != 0) {
			return CustomerConstants.VALIDATION_SUCCESS;
		}
		else {
			return Message + " Can Not Be Null";
		}
	}
	
	//====================Customer Validate=========================

	public static String isValidatioSuccess(CustomerRequest customerRequest) {
		
		String validateMessage = null;
		validateMessage = ifValidate("Customer First Name ", customerRequest.getCustomerFirstName());
		if(!validateMessage.equalsIgnoreCase("success")){
			return validateMessage;
		}
		validateMessage = ifValidate("Customer Last Name ", customerRequest.getCustomerLastName());
		if(!validateMessage.equalsIgnoreCase("success")) {
			return validateMessage;
		}
		validateMessage = ifValidate("Customer Gender", customerRequest.getCustomerGender());
		if(!validateMessage.equalsIgnoreCase("success")) {
			return validateMessage;
		}
		validateMessage = ifValidate("Customer Age", customerRequest.getCustomerAge());
		if(!validateMessage.equalsIgnoreCase("success")) {
			return validateMessage;
		}
		validateMessage = ifValidate("Customer Father Name", customerRequest.getCustomerFatherName());
		if(!validateMessage.equalsIgnoreCase("success")) {
			return validateMessage;
		}
		validateMessage = ifValidate("Customer Mother Name", customerRequest.getCustomerMotherName());
		if(!validateMessage.equalsIgnoreCase("success")) {
			return validateMessage;
		}
		return CustomerConstants.VALIDATION_SUCCESS;
	}
}
