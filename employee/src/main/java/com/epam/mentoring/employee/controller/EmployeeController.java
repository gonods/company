package com.epam.mentoring.employee.controller;

import com.epam.mentoring.employee.dto.EmployeeDto;
import com.epam.mentoring.employee.dto.SumDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface EmployeeController {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
    EmployeeDto create(@RequestBody @Valid EmployeeDto dto);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/employees/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    EmployeeDto update(@RequestBody @Valid EmployeeDto dto, @PathVariable("id") Long id);

    @PutMapping(value = "/employees/{id}/{date}", consumes = MediaType.APPLICATION_JSON_VALUE)
    EmployeeDto updateEndWork(@PathVariable("id") Long id, @PathVariable("date") @DateTimeFormat(pattern = "ddMMyyyy") Date date);

    @PutMapping(value = "/employees/{from}/department/{to}", consumes = MediaType.APPLICATION_JSON_VALUE)
    EmployeeDto updateDepartment(@PathVariable("from") Long fromId, @PathVariable("to") Long toId);

    @PutMapping(value = "/employees/{from}/departmentall/{to}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<EmployeeDto> updateDepartments(@PathVariable("from") Long fromId, @PathVariable("to") Long toId);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/employees/{id}")
    void delete(@PathVariable("id") Long id);

    @GetMapping("/employees")
    List<EmployeeDto> get();

    @GetMapping("/employees/{id}")
    EmployeeDto get(@PathVariable("id") Long id);

    @GetMapping("/employees/{id}/department")
    List<EmployeeDto> getEmployeesByDepartment(@PathVariable("id") Long id);

    @GetMapping("/employees/{id}/chef")
    EmployeeDto getEmployeeChef(@PathVariable("id") Long id);

    @GetMapping("/employees/{email}/email")
    EmployeeDto getEmployeeByEmail(@PathVariable("email") String email);

    @GetMapping("/employees/{id}/sum")
    SumDto getSumSalary(@PathVariable("id") Long id);

    @GetMapping("/{id}/salary")
    Double getSumSalDepartment(@PathVariable("id") Long id);

    @GetMapping("/{id}/employee/count")
    Integer getSumEmployees(@PathVariable("id") Long id);

    @GetMapping("/{id}/namechef")
    String getNameDepartmentChef(@PathVariable("id") Long id);

}




