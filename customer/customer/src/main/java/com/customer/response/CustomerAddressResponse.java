package com.customer.response;
/**
 * @author Padmalochan Ghadei
 */
import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerAddressResponse implements Serializable{

	private static final long serialVersionUID = 8659730884587039230L;

	private String country;

	private String state;

	private String district;

	private String city;

	private String policeStation;

	private String postOffice;

	private String pinCode;
}
