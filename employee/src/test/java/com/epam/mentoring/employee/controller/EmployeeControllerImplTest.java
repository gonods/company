package com.epam.mentoring.employee.controller;

import com.epam.mentoring.employee.IntegrationTest;
import com.epam.mentoring.employee.dto.EmployeeDto;
import com.epam.mentoring.employee.dto.SumDto;
import com.epam.mentoring.employee.model.Employee;
import com.epam.mentoring.employee.repository.EmployeeRepository;
import com.epam.mentoring.employee.util.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@IntegrationTest
class EmployeeControllerImplTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void prepare() {
        employeeRepository.deleteAll();

    }

    @Test
    void create() {


        HttpEntity<EmployeeDto> httpEntity =
                new HttpEntity<EmployeeDto>(TestData.getEmployeeDto());
        ResponseEntity<EmployeeDto> responseEntity =
                restTemplate.postForEntity("/employees", httpEntity, EmployeeDto.class);

        EmployeeDto responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertNotNull(responseBody.getName());
        assertNotNull(responseBody.getEmail());
        assertNotNull((responseBody.getDateBirth()));

    }

    @Test
    void delete() {


        Employee employee = employeeRepository.save(TestData.getEmployee());

        Long id = employee.getId();

        ResponseEntity<EmployeeDto> responseEntity =
                restTemplate.exchange("/employees/{id}", HttpMethod.DELETE, HttpEntity.EMPTY, EmployeeDto.class, id);

        Employee employeeNew = employeeRepository.findEmployeeById(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertTrue(employeeNew.getDeleted());

    }

    @Test
    void update() {


        Employee employee = employeeRepository.save(TestData.getEmployee());

        Long id = employee.getId();

        HttpEntity<EmployeeDto> httpEntityNew =
                new HttpEntity<>(TestData.getNewEmployeeDto());
        ResponseEntity<EmployeeDto> responseEntity =
                restTemplate.exchange("/employees/{id}", HttpMethod.PUT, httpEntityNew, EmployeeDto.class, id);

        EmployeeDto responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseBody.getId());
        assertEquals("Антонн", responseBody.getName());
        assertNotNull(responseBody.getEmail());
        assertNotNull((responseBody.getDateBirth()));
    }

    @Test
    void updateEndWork() {


        Employee employee = employeeRepository.save(TestData.getEmployee());

        Long id = employee.getId();

        int date = 23122024;
        HttpEntity<EmployeeDto> httpEntity =
                new HttpEntity<>(TestData.getNewEmployeeDto());
        restTemplate.exchange("/employees/{id}/{date}", HttpMethod.PUT, httpEntity, EmployeeDto.class, id, date);

        employee = employeeRepository.findEmployeeById(id);

        assertNotNull(employee);
        assertNotNull(employee.getDateEndWork());
        assertEquals("2024-12-23", employee.getDateEndWork().toString());

    }


    @Test
    void updateDep() {


        Employee employee = employeeRepository.save(TestData.getEmployee());

        Long id = employee.getId();

        HttpEntity<EmployeeDto> httpEntity =
                new HttpEntity<EmployeeDto>(TestData.getEmployeeDto());

        Long idDep = Long.valueOf(1004);

        restTemplate.exchange("/employees/{id}/department/{idDep}", HttpMethod.PUT, httpEntity, EmployeeDto.class, id, idDep);
        EmployeeDto responseBody = restTemplate.getForObject("/employees/{id}", EmployeeDto.class, id);

        assertEquals(idDep, responseBody.getDepartmentId());
    }

    @Test
    void get() {


        employeeRepository.save(TestData.getEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());

        ResponseEntity<List<EmployeeDto>> responseEntity = restTemplate.exchange("/employees", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<EmployeeDto>>() {
                });

        List<EmployeeDto> employees = responseEntity.getBody();

        assertNotNull(employees);
        assertEquals(4, employees.size());
        assertEquals("Антон", employees.get(0).getName());
        assertEquals("Семен", employees.get(1).getName());

    }

    @Test
    void getById() {


        employeeRepository.save(TestData.getEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        Employee employee = employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        Long id = employee.getId();

        ResponseEntity<EmployeeDto> responseEntityGetId = restTemplate.getForEntity("/employees/{id}", EmployeeDto.class, id);

        EmployeeDto responseBody = responseEntityGetId.getBody();

        assertNotNull(responseBody);
        assertEquals(employee.getName(), responseBody.getName());


    }

    @Test
    void getEmployeesByDepartment() {


        Long id = employeeRepository.save(TestData.getEmployee()).getDepartmentId();
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());

        ResponseEntity<List<EmployeeDto>> responseEntity = restTemplate.exchange("/employees/{id}/department",
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<EmployeeDto>>() {
                }, id);
        List<EmployeeDto> departmentDtoList = responseEntity.getBody();

        assertNotNull(departmentDtoList);
        assertEquals(4, departmentDtoList.size());

    }

    @Test
    void getEmpChef() {


        employeeRepository.save(TestData.getEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        Employee employee = employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());

        long id = employee.getId();
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.getForEntity("/employees/{id}/chef", EmployeeDto.class, id);
        EmployeeDto employeeDto = responseEntity.getBody();

        assertEquals(true, employeeDto.getChef());
        assertEquals("Антон", employeeDto.getName());
    }


    @Test
    void getEmpEmail() {
        employeeRepository.save(TestData.getEmployee());
        Employee employee = employeeRepository.save(TestData.getNewEmployee());
        String email = employee.getEmail();

        ResponseEntity<EmployeeDto> responseEntityGetId = restTemplate
                .getForEntity("/employees/{email}/email", EmployeeDto.class, email);

        EmployeeDto responseBodyTest = responseEntityGetId.getBody();

        assertNotNull(responseBodyTest);
        assertEquals(email, responseBodyTest.getEmail());

    }

    @Test
    void getSumSalary() {


        employeeRepository.save(TestData.getEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        Employee employee = employeeRepository.save(TestData.getNewEmployee());
        long idDep = employee.getDepartmentId();

        ResponseEntity<SumDto> responseEntity = restTemplate
                .getForEntity("/employees/{idDep}/sum", SumDto.class, idDep);

        SumDto responseBody = responseEntity.getBody();

        assertEquals(325000, responseBody.getSumSalary());
    }


    @Test
    void updateDepartments() {
        Long id = employeeRepository.save(TestData.getEmployee()).getDepartmentId();
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        employeeRepository.save(TestData.getNewEmployee());
        HttpEntity<EmployeeDto> httpEntity = new HttpEntity<>(TestData.getEmployeeDto());
        Long idDep = Long.valueOf(1005);
        restTemplate.exchange("/employees/1004/departmentall/1005", HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<List<EmployeeDto>>() {
        }, id);
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentId((long) 1005);

        assertEquals(4, employees.size());
        assertEquals(idDep, employees.get(2).getDepartmentId());

    }
}