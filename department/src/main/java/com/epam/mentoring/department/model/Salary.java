package com.epam.mentoring.department.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Data
@Entity
@Accessors(chain = true)
public class Salary {

    /**
     * Имя последовательности в БД.
     */
    private static final String ID_SEQ = "salary_id_seq";

    /**
     * id департамента.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    private Long id;

    /**
     * Название департамента.
     */
    private String name;

    /**
     * Дата создания департамента.
     */
    private double salaryFond;

    /**
     * Это поле связывает сотрудника с департаментом.
     */
    private Long departmentId;
}
