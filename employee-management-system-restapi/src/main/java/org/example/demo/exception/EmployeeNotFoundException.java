package org.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	

}