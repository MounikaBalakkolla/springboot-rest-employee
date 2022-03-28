package com.springbootrestemp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootrestemp.exception.EmployeeAlreadyExistsException;
import com.springbootrestemp.exception.EmployeeNotFoundException;
import com.springbootrestemp.model.Employee;
import com.springbootrestemp.service.EmployeeService;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	@PostMapping("/employee")
    public ResponseEntity saveEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @GetMapping("/employees")
    public ResponseEntity<List> getAllEmployees() throws EmployeeNotFoundException {
        return new ResponseEntity<List>((List) employeeService.getAllEmployees(), HttpStatus.OK);
    }
    @GetMapping("employee/{id}")
    public ResponseEntity getEmployeeById(@PathVariable("id") long id) throws EmployeeNotFoundException {
        return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK);
    }
    @PutMapping("employee/{id}/{salary}")
	public ResponseEntity<Employee> updateEmployeeByIdAndSalary(@PathVariable("id") long id,@PathVariable("salary") int salary
												  ,@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployeeByIdAndSalary(employee, id,salary), HttpStatus.OK);
	}
	@DeleteMapping("employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		
		// delete employee from DB
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}
	@GetMapping("/emp")
	public ResponseEntity<List> findByOrderBySalaryDesc(){
		return new ResponseEntity<List>((List) employeeService.findByOrderBySalaryDesc(), HttpStatus.OK);
		
	}

}
