package com.customer.response;
/**
 * @author Padmalochan Ghadei
 */
import java.io.Serializable;
import lombok.Data;

@Data
public class CustomerResponse implements Serializable{
	
	private static final long serialVersionUID = -1616962700155950148L;

	private String customerFirstName;

	private String customerLastName;

	private String customerGender;

	private String customerAge;

	private String customerFatherName;

	private String customerMotherName;
}
