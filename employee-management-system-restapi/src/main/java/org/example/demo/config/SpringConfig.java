package org.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import org.example.demo.model.Employee;
import org.example.demo.repo.EmployeeRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SpringConfig implements CommandLineRunner {

	private final EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("John", "Doe", 10000));
		employees.add(new Employee("Marry", "Public", 11000));
		employees.add(new Employee("Rahul", "Dravid", 12000));
		employeeRepository.saveAll(employees);
	}

}