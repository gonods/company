package com.epam.mentoring.department.dao;

import com.epam.mentoring.department.model.Department;

import java.util.Date;
import java.util.List;

public interface DepartmentDao {

    Department createDepartment(String name, Date createDate, Long upperDepartment);

    Department findDepartmentById(Long id);

    List<Department> findAll();

    void removeDepartment(Integer id);

    Department updateDepartment(Integer id, String name, Date createDate, Long upperDepartment);

    List<Department> findDepartmentsByUpperDepartment(Long id);

    Department findDepartmentByName(String name);

    void deleteAll();

}
