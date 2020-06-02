package com.epam.mentoring.department.dao;

import com.epam.mentoring.department.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Component
public class DepartmentDaoImpl implements DepartmentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
        public Department createDepartment(String name, Date createDate, Long upperDepartment) {
        String sql = "INSERT INTO DEPARTMENT (name, create_date, upper_department) VALUES (?,?,?)";
        jdbcTemplate.update(sql, name, createDate, upperDepartment);
        return findDepartmentByName(name);
    }

    @Override
    public Department findDepartmentById(Long id) {
        try {
            String sql = "SELECT * FROM DEPARTMENT WHERE id = ?";

            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new DepartmentMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Department> findAll() {
        String sql = "SELECT * FROM DEPARTMENT";
        return jdbcTemplate.query(sql, new DepartmentMapper());
    }

    @Override
    public void removeDepartment(Integer id) {
        String sql = "UPDATE DEPARTMENT SET deleted = true  WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Department updateDepartment(Integer id, String name, Date createDate, Long upperDepartment) {
        String sql = "UPDATE DEPARTMENT SET name = ?, create_date = ?, upper_department = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, createDate, upperDepartment, id);

        return findDepartmentByName(name);
    }


    @Override
    public List<Department> findDepartmentsByUpperDepartment(Long id) {
        String sql = "SELECT * FROM DEPARTMENT WHERE upper_department = ?";
        return jdbcTemplate.query(sql, new DepartmentMapper(), id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        String sql = "SELECT * FROM DEPARTMENT WHERE name = ?";
        List<Department> departments = jdbcTemplate.query(sql, new DepartmentMapper(), name);
        return departments.get(0);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("TRUNCATE department CASCADE");

    }
}
