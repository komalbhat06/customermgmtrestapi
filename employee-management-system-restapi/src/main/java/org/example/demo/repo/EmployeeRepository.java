package org.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}