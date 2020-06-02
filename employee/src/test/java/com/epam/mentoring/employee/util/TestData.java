package com.epam.mentoring.employee.util;

import com.epam.mentoring.employee.dto.EmployeeDto;
import com.epam.mentoring.employee.model.Employee;
import com.epam.mentoring.employee.model.Position;
import com.epam.mentoring.employee.model.Sex;
import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public class TestData {

    public static EmployeeDto getEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto()
                .setSurname("Семенов")
                .setName("Антон")
                .setPatronymic("Семенович")
                .setSex("MALE")
                .setDateStartWork(new Date(1451665447567L))
                .setPhoneNumber("+79966196131")
                .setEmail("f9jxjd14@gmail.com")
                .setDateBirth(new Date(14916654567L))
                .setPosition("CHEF")
                .setSalary(125000.00)
                .setChef(true)
                .setDepartmentId((long) 1003)
                .setDeleted(false);
        return employeeDto;
    }

    public static EmployeeDto getNewEmployeeDto() {

        EmployeeDto employeeDto = new EmployeeDto()
                .setSurname("Семеновв")
                .setName("Антонн")
                .setPatronymic("Семеновича")
                .setSex("MALE")
                .setDateBirth(new Date(14516654567L))
                .setPhoneNumber("+79966196131")
                .setEmail("xl9bc5@gmail.com")
                .setDateStartWork(new Date(14916654567L))
                .setPosition("ADMINISTRATOR")
                .setSalary(12000.00)
                .setChef(false)
                .setDepartmentId((long) 1004)
                .setDeleted(false);
        return employeeDto;
    }

    public static EmployeeDto getErrorEmployeeDto() {

        EmployeeDto employeeDto = new EmployeeDto()
                .setSurname("Глазво")
                .setName("Анто")
                .setPatronymic("Семенови1")
                .setSex("MALE")
                .setDateBirth(new Date(14516654567L))
                .setPhoneNumber("+79a66196131")
                .setEmail("xl9bc5@gmail.com")
                .setDateStartWork(new Date(14916654567L))
                .setPosition("CHEF")
                .setSalary(125000.00)
                .setChef(true)
                .setDepartmentId((long) 1004)
                .setDeleted(false);
        return employeeDto;
    }


    public Employee getEmployee() {
        Employee employee = new Employee()
                .setSurname("Семенов")
                .setName("Антон")
                .setPatronymic("Семенович")
                .setSex(Sex.MALE)
                .setDateBirth(new Date(14516654567L))
                .setPhoneNumber("+79966196131")
                .setEmail("f9jxjd14@gmail.com")
                .setDateStartWork(new Date())
                .setPosition(Position.CHEF)
                .setSalary(125000.00)
                .setChef(true)
                .setDepartmentId((long) 1004)
                .setDeleted(false);
        return employee;
    }

    public Employee getNewEmployee() {
        Employee employee = new Employee()
                .setSurname("Иванов")
                .setName("Семен")
                .setPatronymic("Семенович")
                .setSex(Sex.MALE)
                .setDateStartWork(new Date(1451665447567L))
                .setPhoneNumber("+79966196131")
                .setEmail("12f9jxjd14@gmail.com")
                .setDateBirth(new Date(14916654567L))
                .setPosition(Position.OPERATOR)
                .setSalary(100000.00)
                .setChef(false)
                .setDepartmentId((long) 1004)
                .setDeleted(false);
        return employee;
    }
}
