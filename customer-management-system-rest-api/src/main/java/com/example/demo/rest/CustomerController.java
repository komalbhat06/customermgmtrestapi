package com.example.demo.rest;

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

import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.ErrorResponseModel;
import com.example.demo.service.CustomerService;




@RestController

@RequestMapping("/customers")

public class CustomerController {

	private final CustomerService customerService;
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponseModel> handleError(CustomerNotFoundException e)
	{
		ErrorResponseModel model=new ErrorResponseModel();
		model.setErorMessage(e.getMessage());
		model.setErrorCode(HttpStatus.NOT_FOUND.value());
		model.setErrorReportingTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(model);
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
	}

	@GetMapping
	public ResponseEntity<List<Customer>> listCustomers() {
		return ResponseEntity.ok(customerService.listCustomers());
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<?> findCustomerById(@PathVariable("customerId") int customerId) {
		Optional<Customer> optional = customerService.getCustomerById(customerId);

		if (optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("customer with " + customerId + " not found");
		} else {
			return ResponseEntity.status(HttpStatus.FOUND).body(optional.get());
		}
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		Customer e = customerService.updateCustomerById(customer);
		if (e == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("customer with customerId " + customer.getCustomerId() + "not found ");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(e);
		}
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") int customerId) {

		customerService.deleteCustomerById(customerId);
		return ResponseEntity.status(HttpStatus.OK).body("customerdeleted..");

	}

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	

}