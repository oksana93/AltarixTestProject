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
    private Integer j_id;

    @Basic
    @Column(name = "j_definition", nullable = false, length = 200)
    private String j_definition;

    @Override
    public Integer getId() {
        return j_id;
    }
}