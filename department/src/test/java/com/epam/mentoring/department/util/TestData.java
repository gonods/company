package com.epam.mentoring.department.util;


import com.epam.mentoring.department.dto.DepartmentDto;
import com.epam.mentoring.department.model.Department;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@UtilityClass
public class TestData {

    public static DepartmentDto getDepartmentDto() {
        return new DepartmentDto()
                        .setName("Департамент Самары1");
    }

    public static DepartmentDto getNewDepartmentDto() {
        return new DepartmentDto()
                        .setName("Департамент Самары2")
                        .setCreateDate(new Date(1451665447567L));
    }

    public static DepartmentDto getNew1DepartmentDto() {
        return new DepartmentDto()
                        .setName("Департамент Самары3")
                        .setCreateDate(new Date(1451665447567L));
    }

    public static DepartmentDto getNew2DepartmentDto() {
        return new DepartmentDto()
                        .setName("Департамент Самары4")
                        .setCreateDate(new Date(1451665447567L));
    }


    public List<Department> createDepartments() {

        List<Department> list = new ArrayList<>();
        list.add(new Department().setName("Департамент Самары").setCreateDate(new Date()));
        list.add(new Department().setName("Департамент Образования").setCreateDate(new Date()));
        list.add(new Department().setName("Департамент Городского Хозяйства").setCreateDate(new Date()));
        list.add(new Department().setName("Департамент Транспорта Администрации").setCreateDate(new Date()));
        list.add(new Department().setName("Департамент управления имущества").setCreateDate(new Date()));
        list.add(new Department().setName("Департамент благоустройства и экологии").setCreateDate(new Date()));
        list.add(new Department().setName("Департамент финансов и экономического").setCreateDate(new Date()));
        list.add(new Department().setName("Департамент Физической Культуры").setCreateDate(new Date()));

        return list;
    }

    public Department getDepartment() {
        return new Department().setId((long) 1001).setName("Департамент Самары");

    }

    public Department getDepartment1() {
        return new Department().setId((long) 1002).setName("Департамент Образования");
    }

    public Department getDepartment3() {
        return new Department().setId((long) 1001).setName("Департамент Городского Хозяйства");
    }

    public Department getDepartment4() {
        return new Department().setId((long) 654825).setName("Департамент Транспорта Администрации");
    }


}
