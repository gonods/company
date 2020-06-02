package com.epam.mentoring.department.service;

import com.epam.mentoring.department.dto.DepartmentDto;
import com.epam.mentoring.department.model.Department;

import java.util.List;

public interface DepartmentService {

    Department create(DepartmentDto dto);

    Department update(DepartmentDto dto, Long id);

    Department update(Long id, Long idUp);

    void delete(Long id);

    List<Department> get();

    Department get(Long id);

    List<Department> getLower(Long id);

    List<Department> getAllLowerDep(Long id);

    List<Department> getAllUpperDep(Long id);

    List<Department> getLowers(List<Department> departmentList, Long id);

    List<Department> getUppers(List<Department> departmentList, Long id);

    Department getName(String name);
}
