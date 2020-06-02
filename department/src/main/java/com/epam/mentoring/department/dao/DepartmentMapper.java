package com.epam.mentoring.department.dao;

import com.epam.mentoring.department.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = new Department();

        department.setId(resultSet.getLong("id"));
        department.setName(resultSet.getString("name"));
        department.setCreateDate(resultSet.getDate("create_date"));
        department.setUpperDepartment(resultSet.getLong("upper_department"));
        department.setDeleted(resultSet.getBoolean("deleted"));
        return department;
    }
}
