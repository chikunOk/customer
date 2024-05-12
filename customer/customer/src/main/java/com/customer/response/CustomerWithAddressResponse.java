package com.customer.response;
/**
 * @author Padmalochan Ghadei
 */
import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CustomerWithAddressResponse implements Serializable{

	private static final long serialVersionUID = -2141329164396373765L;

	private CustomerResponse customerResponse;
	
	private List<CustomerAddressResponse> customerAddressResponses;
}
