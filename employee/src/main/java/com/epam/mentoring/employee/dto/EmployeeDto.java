package com.epam.mentoring.employee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Этот класс дто от сущности сотрудника.
 */
@Data
@Accessors(chain = true)
public class EmployeeDto {
    /**
     * id сотрудника.
     */
    private Long id;

    /**
     * Фамилия сотрудника.
     */
    @NotNull
    @Pattern(regexp = "^[А-ЯЁ][а-яё\\-]*$")
    private String surname;

    /**
     * Имя сотрудника.
     */
    @NotNull
    @Pattern(regexp = "^[А-ЯЁ][а-яё\\-]*$")
    private String name;

    /**
     * Отчество сотрудника
     * (необязательно для заполнения).
     */
    @Pattern(regexp = "^[А-ЯЁ][а-яё\\-]*$")
    private String patronymic;

    /**
     * Пол сотрудника.
     */
    private String sex;

    /**
     * Дата рождения сотрудника.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date dateBirth;

    /**
     * Номер телефона сотрудника.
     */
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phoneNumber;

    /**
     * Электронная почта сотрудника.
     */
    @Email
    private String email;

    /**
     * Дата начала работы.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateStartWork;

    /**
     * Должность сотрудника.
     */
    private String position;

    /**
     * Размер заработной платы сотрудника.
     */
    @Min(value = 0)
    private Double salary;

    /**
     * Это поле обозначает является
     * ли сотрудник руководителем департамента.
     */
    private Boolean chef;

    /**
     * Это поле обозначает удаленного сотрудника.
     */
    private Boolean deleted;

    /**
     * Id департамента в котором состоит сотрудник.
     */
    private Long departmentId;
}
