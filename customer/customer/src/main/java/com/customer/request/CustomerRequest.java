package com.customer.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerRequest implements Serializable {

	private static final long serialVersionUID = 7626348834762201694L;

	private String customerFirstName;

	private String customerLastName;

	private String customerGender;

	private String customerAge;

	private String customerFatherName;

	private String customerMotherName;

}
