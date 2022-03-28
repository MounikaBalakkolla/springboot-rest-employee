package com.springbootrestemp.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.springbootrestemp.exception.EmployeeAlreadyExistsException;
import com.springbootrestemp.exception.EmployeeNotFoundException;
import com.springbootrestemp.model.Employee;
import com.springbootrestemp.repository.EmployeeRepository;

public interface EmployeeService {
	Employee saveEmployee(Employee employee) throws EmployeeAlreadyExistsException;
	List getAllEmployees() throws EmployeeNotFoundException;
	Employee getEmployeeById(long id) throws EmployeeNotFoundException;
	void deleteEmployee(long id);
	List<Employee> findByOrderBySalaryDesc();
	Employee updateEmployeeByIdAndSalary(Employee employee, long id, int salary);
	
}
