package com.cognizant.stockmarket.excelupload.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Excel Format")
public class InvalidFormatException extends RuntimeException{

	public InvalidFormatException(){
		super();
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid Data";
	}
	
}
