package com.epam.mentoring.department.service;

import com.epam.mentoring.department.controller.EmployeeFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmployeeServiceImpl {

    private final EmployeeFeign employeeFeign;

    @HystrixCommand(fallbackMethod = "fallbackGetSumSalary")
    public Double getSumSalary(Long id) {
        return employeeFeign.getSumSalDepartment(id);
    }

    public Double fallbackGetSumSalary(Long id) {
        return 15.00;
    }

    @HystrixCommand(fallbackMethod = "fallbackGetSumEmployees")
    public Integer getSumEmployees(Long id) {
        return employeeFeign.getSumEmployees(id);
    }

    public Integer fallbackGetSumEmployees(Long id) {
        return 1;
    }

    @HystrixCommand(fallbackMethod = "fallbackGetChefName")
    public String getChefName(Long id) {
        return employeeFeign.getNameDepartmentChef(id);
    }

    public String fallbackGetChefName(Long id) {
        return "имя";
    }

}

