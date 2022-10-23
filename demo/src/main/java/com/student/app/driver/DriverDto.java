package com.student.app.driver;

import com.student.app.company.model.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverDto {

    private String name;

    private Integer age;

    private Company company;
}
