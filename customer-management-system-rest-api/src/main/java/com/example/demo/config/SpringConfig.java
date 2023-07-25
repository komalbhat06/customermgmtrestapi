package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;

@Configuration
public class SpringConfig implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public SpringConfig(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("John", "Doe", "john@gmail.com"));
        customers.add(new Customer("Marry", "Public", "mary@gmail.com"));
        
        customerRepository.saveAll(customers);
    }
}
