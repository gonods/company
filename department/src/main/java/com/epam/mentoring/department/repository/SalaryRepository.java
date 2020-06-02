package com.epam.mentoring.department.repository;

import com.epam.mentoring.department.model.Salary;
import org.springframework.data.repository.CrudRepository;

public interface SalaryRepository extends CrudRepository<Salary, Long> {

    Salary findSalaryByDepartmentId(Long id);

}
