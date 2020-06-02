package com.epam.mentoring.employee.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SumDto {

    private double sumSalary;

    public SumDto(double sumSalary) {
        this.sumSalary = sumSalary;
    }

}
