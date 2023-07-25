package org.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.example.demo.exception.EmployeeNotFoundException;
import org.example.demo.model.Employee;
import org.example.demo.model.ErrorResponseModel;
import org.example.demo.service.EmployeeService;




@RestController

@RequestMapping("/employees")

public class EmployeeController {

	private final EmployeeService employeeService;
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponseModel> handleError(EmployeeNotFoundException e)
	{
		ErrorResponseModel model=new ErrorResponseModel();
		model.setErorMessage(e.getMessage());
		model.setErrorCode(HttpStatus.NOT_FOUND.value());
		model.setErrorReportingTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(model);
	}

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employee));
	}

	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		return ResponseEntity.ok(employeeService.listEmployees());
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<?> findEmployeeById(@PathVariable("employeeId") int employeeId) {
		Optional<Employee> optional = employeeService.getEmployeeById(employeeId);

		if (optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employee with " + employeeId + " not found");
		} else {
			return ResponseEntity.status(HttpStatus.FOUND).body(optional.get());
		}
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		Employee e = employeeService.updateEmployeeById(employee);
		if (e == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("employee with employeeId " + employee.getEmployeeId() + "not found ");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(e);
		}
	}

	@DeleteMapping("/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") int employeeId) {

		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body("employee deleted..");

	}

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	

}