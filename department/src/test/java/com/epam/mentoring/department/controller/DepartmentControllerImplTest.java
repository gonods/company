package com.epam.mentoring.department.controller;

import com.epam.mentoring.department.IntegrationTest;
import com.epam.mentoring.department.dao.DepartmentDaoImpl;
import com.epam.mentoring.department.dto.DepartmentDto;
import com.epam.mentoring.department.model.Department;
import com.epam.mentoring.department.util.TestData;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
class DepartmentControllerImplTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DepartmentDaoImpl departmentRepository;


    @BeforeEach
    void prepare() {
        departmentRepository.deleteAll();

    }

    @Test
    void get() {

        departmentRepository.createDepartment(TestData.getDepartment().getName(), new Date(), TestData.getDepartment().getUpperDepartment());
        departmentRepository.createDepartment(TestData.getDepartment1().getName(), new Date(), TestData.getDepartment1().getUpperDepartment());
        departmentRepository.createDepartment(TestData.getDepartment3().getName(), new Date(), TestData.getDepartment3().getUpperDepartment());
        departmentRepository.createDepartment(TestData.getDepartment4().getName(), new Date(), TestData.getDepartment4().getUpperDepartment());

        ResponseEntity<List<DepartmentDto>> responseEntity = restTemplate.exchange("/departments",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<DepartmentDto>>() {
                });
        List<DepartmentDto> departments = responseEntity.getBody();
        assertNotNull(departments);
        assertEquals(4, departments.size());
        assertEquals("Департамент Самары", departments.get(0).getName());
        assertEquals("Департамент Образования", departments.get(1).getName());
    }

    @Test
    void getId() {


        departmentRepository.createDepartment(TestData.getDepartment().getName(), new Date(), TestData.getDepartment().getUpperDepartment());
        departmentRepository.createDepartment(TestData.getDepartment1().getName(), new Date(), TestData.getDepartment1().getUpperDepartment());
        Department department = departmentRepository.createDepartment(TestData.getDepartment3().getName(),
                new Date(), TestData.getDepartment3().getUpperDepartment());
        departmentRepository.createDepartment(TestData.getDepartment4().getName(), new Date(), TestData.getDepartment4().getUpperDepartment());
        Long id = department.getId();

        ResponseEntity<DepartmentDto> responseEntityGetId = restTemplate
                .getForEntity("/departments/{id}", DepartmentDto.class, id);

        DepartmentDto departmentDto = responseEntityGetId.getBody();

        assertNotNull(departmentDto);
        assertEquals(id, departmentDto.getId());
        assertEquals(department.getName(), departmentDto.getName());
    }

    @Test
    void create() {

        HttpEntity<DepartmentDto> httpEntity = new HttpEntity<>(TestData.getDepartmentDto().setCreateDate(new Date()));

        ResponseEntity<DepartmentDto> responseEntity = restTemplate
                        .postForEntity("/departments", httpEntity, DepartmentDto.class);

        DepartmentDto responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertNotNull(responseBody.getCreateDate());
    }

    @Test
    void update() {

        Department department = departmentRepository.createDepartment(TestData.getDepartment3().getName(),
                new Date(), TestData.getDepartment3().getUpperDepartment());

        Long id = department.getId();

        HttpEntity<DepartmentDto> httpEntityNew = new HttpEntity<>(TestData.getNewDepartmentDto());
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.exchange("/departments/{id}", HttpMethod.PUT,
                        httpEntityNew, DepartmentDto.class, id);

        DepartmentDto departmentDto = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(departmentDto.getId());
        assertEquals("Департамент Самары2", departmentDto.getName());
        assertEquals(departmentDto.getId(), id);
    }

    @Test
    void delete() {

        Department department = departmentRepository.createDepartment(TestData.getDepartment3().getName(),
                new Date(), TestData.getDepartment3().getUpperDepartment());

        Long id = department.getId();

        ResponseEntity<DepartmentDto> responseEntity = restTemplate.exchange("/departments/{id}", HttpMethod.DELETE,
                        null, DepartmentDto.class, id);
        department = departmentRepository.findDepartmentById(id);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertTrue(department.getDeleted());
    }

    @Test
    void getLower() {

        List<Department> departments = createDepartments();
        Long id = departments.get(2).getId();

        ResponseEntity<List<DepartmentDto>> responseEntity = restTemplate.exchange("/departments/{id}/lower",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<DepartmentDto>>() {
                        }, id);
        List<DepartmentDto> responseBody = responseEntity.getBody();

        assertEquals(responseBody.get(0).getUpperDepartment(), id);

    }

    @Test
    void updateUpDep() {

        List<Department> departments = createDepartments();
        Long id = departments.get(7).getUpperDepartment();

        Long idUp = id - 6;
        HttpEntity<DepartmentDto> httpEntity = new HttpEntity<>(TestData.getDepartmentDto());
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.exchange("/departments/{id}/upper/{idUp}",
                        HttpMethod.PUT, httpEntity, DepartmentDto.class, id, idUp);
        DepartmentDto responseBody = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseBody.getUpperDepartment(), idUp);
        assertEquals(responseBody.getId(), id);
    }

    @Test
    void getLowers() {

        List<Department> departments = createDepartments();
        Department department = departments.get(0);
        Long id = department.getId();
        ResponseEntity<List<DepartmentDto>> responseEntity = restTemplate
                        .exchange("/departments/{id}/lowers", HttpMethod.GET, null, new ParameterizedTypeReference<List<DepartmentDto>>() {
                        }, id);
        List<DepartmentDto> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(7, responseBody.size());

    }

    @Test
    void getUppers() {

        List<Department> departments = createDepartments();
        Department department = departments.get(5);
        Long id = department.getId();
        ResponseEntity<List<DepartmentDto>> responseEntity = restTemplate
                        .exchange("/departments/{id}/uppers", HttpMethod.GET, null, new ParameterizedTypeReference<List<DepartmentDto>>() {
                        }, id);
        List<DepartmentDto> list = responseEntity.getBody();

        assertEquals(5, list.size());
    }

    @Test
    void getName() {

        List<Department> departments = createDepartments();
        Department department = departments.get(5);
        String name = department.getName();

        ResponseEntity<DepartmentDto> responseEntity = restTemplate
                        .getForEntity("/departments/{name}/name", DepartmentDto.class, name);
        DepartmentDto responseBody = responseEntity.getBody();

        assertEquals(name, responseBody.getName());
    }

    public List<Department> createDepartments() {
        Long id = null;
        List<Department> list = TestData.createDepartments();
        List<Department> list2 = new ArrayList<>();
        for (Department dto : list) {
            Department department = departmentRepository.createDepartment(dto.getName(), new Date(), id);


            list2.add(department);
            id = department.getId();
        }
        return list2;
    }

}