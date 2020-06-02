package com.epam.mentoring.employee.controller;

import com.epam.mentoring.employee.dto.EmployeeDto;
import com.epam.mentoring.employee.dto.SumDto;
import com.epam.mentoring.employee.model.Employee;
import com.epam.mentoring.employee.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping
@RestController
public class EmployeeControllerImpl implements EmployeeController {

    private EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeControllerImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public EmployeeDto create(EmployeeDto dto) {
        return employeeService.from(employeeService.create(dto));
    }

    @Override
    public EmployeeDto update(EmployeeDto dto, Long id) {
        return employeeService.from(employeeService.update(dto, id));
    }

    @Override
    public void delete(Long id) {
        employeeService.delete(id);
    }

    @Override
    public List<EmployeeDto> get() {
        return employeeService.from(employeeService.get());
    }

    @Override
    public EmployeeDto get(Long id) {
        return employeeService.from(employeeService.get(id));
    }

    @Override
    public EmployeeDto updateEndWork(Long id, Date date) {
        return employeeService.from(employeeService.updateEndWork(id, date));
    }

    @Override
    public EmployeeDto updateDepartment(Long from, Long to) {
        return employeeService.from(employeeService.updateDep(from, to));
    }

    @Override
    public List<EmployeeDto> updateDepartments(Long from, Long to) {
        List<Employee> list = employeeService.updateDepAll(from, to);
        return employeeService.from(list);
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(Long id) {
        return employeeService.from(employeeService.getEmpDep(id));
    }

    @Override
    public EmployeeDto getEmployeeChef(Long id) {
        return employeeService.from(employeeService.getEmpChef(id));
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String email) {
        return employeeService.from(employeeService.getEmpEmail(email));
    }

    @Override
    public SumDto getSumSalary(Long id) {
        return employeeService.getSumDep(id);
    }

    @Override
    public Double getSumSalDepartment(Long id) {
        return employeeService.getSumDepartment(id);
    }

    @Override
    public Integer getSumEmployees(Long id) {
        return employeeService.getSumEmployees(id);
    }

    @Override
    public String getNameDepartmentChef(Long id) {
        return employeeService.getNameDepartmentChef(id);
    }
}
