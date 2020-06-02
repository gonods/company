package com.epam.mentoring.department.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * Этот класс сущность департамента.
 */

@Data
@Accessors(chain = true)
public class Department {

    /**
     * id департамента.
     */
    private Long id;

    /**
     * Название департамента.
     */
    private String name;

    /**
     * Дата создания департамента.
     */

    private Date createDate;
    /**
     * id департамента в который он входит.
     */
    private Long upperDepartment;

    /**
     * Пометка удаленного департамента.
     */
    private Boolean deleted = false;
    
}

