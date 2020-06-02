package com.epam.mentoring.employee.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Этот класс сущность сотрудника.
 */
@Data
@Entity
@Accessors(chain = true)
@Audited
public class Employee {

    /**
     * Имя последовательности в БД.
     */
    private static final String ID_SEQ = "employee_id_seq";

    /**
     * id сотрудника.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ)
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    private Long id;

    /**
     * Фамилия сотрудника.
     */
    private String surname;

    /**
     * Имя сотрудника.
     */
    private String name;

    /**
     * Отчество сотрудника (необязательно для заполнения).
     */
    private String patronymic;

    /**
     * Пол сотрудника.
     */
    @Enumerated(EnumType.STRING)
    private Sex sex;

    /**
     * Дата рождения сотрудника.
     */
    @Temporal(TemporalType.DATE)
    private Date dateBirth;

    /**
     * Номер телефона сотрудника.
     */
    private String phoneNumber;

    /**
     * Электронная почта сотрудника.
     */
    private String email;

    /**
     * Дата начала работы.
     */
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date dateStartWork;

    /**
     * Дата увольнения сотрудника.
     */
    @Temporal(TemporalType.DATE)
    private Date dateEndWork;

    /**
     * Должность сотрудника.
     */
    @Enumerated(EnumType.STRING)
    private Position position;

    /**
     * Размер заработной платы сотрудника.
     */
    private Double salary;

    /**
     * Это поле обозначает является ли сотрудник руководителем департамента.
     */
    private Boolean chef;

    /**
     * Это поле обозначает удаленного сотрудника.
     */
    private Boolean deleted = false;

    /**
     * Это поле связывает сотрудника с департаментом.
     */

    @JoinColumn(name = "department_id")
    private Long departmentId;

}
