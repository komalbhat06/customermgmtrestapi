package com.example.demo.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponseModel {

	
	private long errorReportingTime;
	
	private String erorMessage;
	
	private int errorCode;
}
