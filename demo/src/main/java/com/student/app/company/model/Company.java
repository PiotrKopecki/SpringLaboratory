package com.student.app.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.student.app.driver.model.Driver;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "company")
public class Company {
    @Id
    private String name;

    @Column(name = "new_name")
    private String newName;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Driver> drivers = new ArrayList<>();
}
