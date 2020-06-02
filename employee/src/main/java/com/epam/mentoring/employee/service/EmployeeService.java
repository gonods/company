package com.epam.mentoring.employee.service;


import com.epam.mentoring.employee.dto.EmployeeDto;
import com.epam.mentoring.employee.dto.SumDto;
import com.epam.mentoring.employee.model.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeService {

    boolean validationSalary(EmployeeDto dto);

    Employee create(EmployeeDto dto);

    Employee update(EmployeeDto dto, Long id);

    void delete(Long id);

    List<Employee> get();

    Employee get(Long id);

    List<Employee> getEmpDep(Long id);

    Employee updateEndWork(Long id, Date date);

    Employee updateDep(Long id, Long dep);

    List<Employee> updateDepAll(Long from, Long to);

    Employee getEmpChef(Long id);

    Employee getEmpEmail(String email);

    SumDto getSumDep(long id);
}
