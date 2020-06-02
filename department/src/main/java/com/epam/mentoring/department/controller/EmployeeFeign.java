package com.epam.mentoring.department.controller;

import com.epam.mentoring.employee.controller.EmployeeController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "employee")
public interface EmployeeFeign extends EmployeeController {
}
