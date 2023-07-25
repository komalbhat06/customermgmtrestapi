package org.example.demo.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import org.example.demo.exception.EmployeeNotFoundException;
import org.example.demo.model.Employee;
import org.example.demo.repo.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service

public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> listEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(int employeeId) {
		// TODO Auto-generated method stub

		Optional<Employee> emOptional = employeeRepository.findById(employeeId);
		if (emOptional == null) {
			return null;
		} else {
			return emOptional;
		}
	}

	 @Override
	    public Employee updateEmployeeById(Employee employee) {
	        // TODO Auto-generated method stub
	        int employeeId = employee.getEmployeeId();
	        if (employeeId <= 0) {
	            throw new IllegalArgumentException("Invalid employeeId. Must be greater than 0.");
	        }

	        Optional<Employee> oEmployee = employeeRepository.findById(employeeId);

	        if (oEmployee.isEmpty()) {
	            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found");
	        } else {
	            Employee existingEmployee = oEmployee.get();
	            existingEmployee.setFirstName(employee.getFirstName());
	            existingEmployee.setLastName(employee.getLastName());
	            existingEmployee.setSalary(employee.getSalary());
	            return employeeRepository.save(existingEmployee);
	        }
	    }


	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
		Optional<Employee> oEmployee = employeeRepository.findById(employeeId);

		if (!oEmployee.isEmpty()) {
			
			
			employeeRepository.deleteById(employeeId);
		}
		else
		{
			throw new EmployeeNotFoundException("employee with given id not found..");
		}
		
	}

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

}