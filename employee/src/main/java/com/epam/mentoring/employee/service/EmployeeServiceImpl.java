package com.epam.mentoring.employee.service;

import com.epam.mentoring.employee.dto.EmployeeDto;
import com.epam.mentoring.employee.dto.SumDto;
import com.epam.mentoring.employee.model.Employee;
import com.epam.mentoring.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final MapperFacade mapperFacade;

    public Employee from(EmployeeDto dto) {

        if (validationSalary(dto) && validationDate(dto) && validationChefAmount(dto)) {
            return mapperFacade.map(dto, Employee.class);
        } else {
            return null;
        }
    }

    public EmployeeDto from(Employee employee) {
        return mapperFacade.map(employee, EmployeeDto.class);
    }

    public List<EmployeeDto> from(List<Employee> employees) {
        return mapperFacade.mapAsList(employees.toArray(), EmployeeDto.class);
    }

    public boolean validationDate(EmployeeDto dto) {
        return dto.getDateBirth().before(dto.getDateStartWork());
    }

    public boolean validationChefAmount(EmployeeDto dto) {
        if (dto.getChef() != null && dto.getChef()) {
            List<Employee> list = employeeRepository.findEmployeesByDepartmentId(dto.getDepartmentId());
            if (!list.isEmpty()) {
                for (Employee employee : list) {
                    if (employee.getChef() != null && employee.getChef()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean validationSalary(EmployeeDto dto) {
        List<Employee> list = employeeRepository.findEmployeesByDepartmentId(dto.getDepartmentId());
        double salaryChef = 0;
        for (Employee employee : list) {
            if (employee.getChef() != null && employee.getChef()) {
                salaryChef = employee.getSalary();
            }
        }
        return salaryChef == 0 || dto.getSalary() < salaryChef;
    }

    @Override
    public Employee create(EmployeeDto dto) {

        Employee employee = from(dto);
        employee.setDepartmentId(dto.getDepartmentId());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(EmployeeDto dto, Long id) {

        Employee employee = from(dto);
        employee.setDepartmentId(dto.getDepartmentId());
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {

        Employee employee = employeeRepository.findEmployeeById(id);
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> get() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee get(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public List<Employee> getEmpDep(Long id) {
        return employeeRepository.findEmployeesByDepartmentId(id);
    }

    @Override
    public Employee updateEndWork(Long id, Date date) {

        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee.getDateStartWork().before(date)) {
            employee.setDeleted(true);
            employee.setDateEndWork(date);
            return employeeRepository.save(employee);
        } else {
            return null;
        }
    }

    @Override
    public Employee updateDep(Long id, Long dep) {

        Employee employee = employeeRepository.findEmployeeById(id);
        employee.setDepartmentId(dep);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> updateDepAll(Long from, Long to) {

        List<Employee> employees = employeeRepository.findEmployeesByDepartmentId(from);
        List<Employee> employeeList = new ArrayList<>();

        for (Employee employee : employees) {
            employee.setDepartmentId(to);
            employeeList.add(employeeRepository.save(employee));

        }
        return employeeList;
    }

    @Override
    public Employee getEmpChef(Long id) {

        List<Employee> employees = employeeRepository
                .findEmployeesByDepartmentId(employeeRepository.findEmployeeById(id).getDepartmentId());
        Employee chef = null;
        for (Employee employee : employees) {
            if (employee.getChef() != null && employee.getChef()) {
                chef = employee;
            }
        }
        return chef;
    }

    @Override
    public Employee getEmpEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email);
    }

    @Override
    public SumDto getSumDep(long id) {
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentId(id);
        if (employees == null) {
            return new SumDto(0.00);
        } else {
            double sum = 0.00;
            for (Employee employee : employees) {
                sum += employee.getSalary();
            }
            return new SumDto(sum);
        }
    }

    public double getSumDepartment(long id) {
        return getSumDep(id).getSumSalary();
    }

    public Integer getSumEmployees(Long id) {
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentId(id);
        return employees.size();
    }

    public String getNameDepartmentChef(Long id) {
        Employee chef = getEmpChef(id);
        return chef.getSurname() + " " + chef.getName() + " " + chef.getPatronymic();
    }
}