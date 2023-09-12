package com.userlocation.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDetails {
	
	private Date timpeStamp;
	private String details;
	private String message;

}
