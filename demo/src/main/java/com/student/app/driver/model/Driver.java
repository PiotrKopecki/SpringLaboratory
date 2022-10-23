package com.student.app.driver.model;

import com.student.app.company.model.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@ToString
@SuperBuilder
@Entity
@NoArgsConstructor
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @ManyToOne
    @JoinColumn(name = "company_name", nullable = false, referencedColumnName = "name")
    private Company company;
}
