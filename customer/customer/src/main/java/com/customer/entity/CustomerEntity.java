package com.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String customerFirstName;
	
	@Column(name = "last_name")
	private String customerLastName;
	
	@Column(name = "gender")
	private String customerGender;
	
	@Column(name = "age")
	private String customerAge;
	
	@Column(name = "father_Name")
	private String customerFatherName;
	
	@Column(name = "mother_Name")
	private String customerMotherName;
	
}
