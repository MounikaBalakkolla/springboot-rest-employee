package com.springbootrestemp.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springbootrestemp.exception.EmployeeAlreadyExistsException;
import com.springbootrestemp.exception.EmployeeNotFoundException;
import com.springbootrestemp.model.Employee;
import com.springbootrestemp.repository.EmployeeRepository;
import com.springbootrestemp.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	private Employee employee;
	
	@Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee saveEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            throw new EmployeeAlreadyExistsException();
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }
    @Override
    public List getAllEmployees() {
        return (List) employeeRepository.findAll();
    }
    @Override
    public Employee getEmployeeById(long id) throws EmployeeNotFoundException {
        Employee employee;
        if (employeeRepository.findById(id).isEmpty()) {
            throw new EmployeeNotFoundException();
        } else {
            employee = employeeRepository.findById(id).get();
        }
        return employee;
    }
    @Override
	public Employee updateEmployeeByIdAndSalary(Employee employee, long id,int salary) {
		
		// we need to check whether employee with given id is exist in DB or not
		Employee existingEmployee = employeeRepository.findByIdAndSalary(id,salary).orElseThrow(
				() -> new EmployeeNotFoundException()); 
		
		existingEmployee.setName(employee.getName());
		existingEmployee.setSalary(employee.getSalary());
		existingEmployee.setDesignation(employee.getDesignation());
		// save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		
		// check whether a employee exist in a DB or not
		employeeRepository.findById(id).orElseThrow(() -> 
								new EmployeeNotFoundException());
		employeeRepository.deleteById(id);
	}
	@Override
	public List<Employee> findByOrderBySalaryDesc(){
		return employeeRepository.findAll(Sort.by("salary").descending().and(Sort.by("name")));
		
	}

}
