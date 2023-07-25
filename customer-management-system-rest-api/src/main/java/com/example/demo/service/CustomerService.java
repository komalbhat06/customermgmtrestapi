package com.example.demo.service;


import java.util.List;
import java.util.Optional;


import com.example.demo.model.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    List<Customer> listCustomers();

    Optional<Customer> getCustomerById(int customerId);

    Customer updateCustomerById(Customer customer);

    void deleteCustomerById(int customerId);
}
