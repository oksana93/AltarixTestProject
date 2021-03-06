package com.example.altarix.job;

import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "jobs")
public class Job implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Basic
    @Column(name = "definition", nullable = false, length = 200)
    private String definition;

    @Override
    public Integer getId() {
        return id;
    }
}