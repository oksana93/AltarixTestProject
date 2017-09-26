package com.example.altarix.department;

import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "departments")
public class Department implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    private Integer d_id;

    @Basic
    @Column(name = "d_name", nullable = false, length = 60)
    private String d_name;

    @Basic
    @Column(name = "d_date", nullable = false)
    private Date d_date;

    @Override
    public Integer getId() {
        return d_id;
    }
}

