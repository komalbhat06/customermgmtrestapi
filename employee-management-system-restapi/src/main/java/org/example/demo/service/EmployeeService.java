package org.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.example.demo.model.Employee;

public interface EmployeeService {
	Employee createEmployee(Employee employee);

	List<Employee> listEmployees();

	Optional<Employee> getEmployeeById(int employeeId);
	
	Employee updateEmployeeById(Employee employee);
	void deleteEmployee(int employeeId);

}