package com.springbootrestemp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootrestemp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	List<Employee>findByOrderBySalaryDesc(int salary,Sort sort);

	Optional<Employee> findByIdAndSalary(long id, int salary);
}
