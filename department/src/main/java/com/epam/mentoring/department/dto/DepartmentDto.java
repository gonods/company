package com.epam.mentoring.department.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Этот класс дто от сущности департамента.
 */
@Data
@Accessors(chain = true)
public class DepartmentDto {
    /**
     * id Департамента.
     */
    private Long id;

    /**
     * Название департамента.
     */
    private String name;

    /**
     * Дата создания департамента.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createDate;

    /**
     * Имя руководителя департамента.
     */
    private String chefName;

    /**
     * Колличество сотрудников департамента.
     */
    private Long amountEmployee;

    /**
     * id департамента в который он входит.
     */
    private Long upperDepartment;

}
