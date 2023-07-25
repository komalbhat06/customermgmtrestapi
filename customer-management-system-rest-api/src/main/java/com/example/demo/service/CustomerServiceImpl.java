package com.example.demo.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;

import lombok.AllArgsConstructor;

@Service

public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub

		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> listCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(int customerId) {
		// TODO Auto-generated method stub

		Optional<Customer> emOptional = customerRepository.findById(customerId);
		if (emOptional == null) {
			return null;
		} else {
			return emOptional;
		}
	}

	 @Override
	    public Customer updateCustomerById(Customer customer) {
	        // TODO Auto-generated method stub
	        int customerId = customer.getCustomerId();
	        if (customerId <= 0) {
	            throw new IllegalArgumentException("Invalid customerId. Must be greater than 0.");
	        }

	        Optional<Customer> oCustomer = customerRepository.findById(customerId);

	        if (oCustomer.isEmpty()) {
	            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
	        } else {
	            Customer existingCustomer = oCustomer.get();
	            existingCustomer.setFirstName(customer.getFirstName());
	            existingCustomer.setLastName(customer.getLastName());
	            existingCustomer.setEmail(customer.getEmail());
	            return customerRepository.save(existingCustomer);
	        }
	    }


	@Override
	public void deleteCustomerById(int customerId) {
		// TODO Auto-generated method stub
		
		Optional<Customer> oCustomer = customerRepository.findById(customerId);

		if (!oCustomer.isEmpty()) {
			
			
			customerRepository.deleteById(customerId);
		}
		else
		{
			throw new CustomerNotFoundException("customer with given id not found..");
		}
		
	}

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

}