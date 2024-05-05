package com.customer.finalResponse;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FinalResponse {

	public String Message;

	private HttpStatus httpStatus;

	private Object responsePayLoad;
}
