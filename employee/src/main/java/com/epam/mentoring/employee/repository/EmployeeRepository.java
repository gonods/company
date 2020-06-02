package com.epam.mentoring.employee.repository;

import com.epam.mentoring.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findEmployeeById(Long id);

    List<Employee> findEmployeesByDepartmentId(Long id);

    Employee findEmployeeByEmail(String email);
}
