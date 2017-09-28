package com.example.altarix.employee;

import com.example.altarix.department.Department;
import com.example.altarix.job.Job;
import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.sql.Date;


@Data
@Entity
@Table(name = "employees")
public class Employee implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Basic
    @Column(name = "firstname", nullable = false, length = 60)
    private String firstname;

    @Basic
    @Column(name = "lastname", nullable = false, length = 60)
    private String lastname;

    @Basic
    @Column(name = "patronymic", nullable = false, length = 60)
    private String patronymic;

    @Basic
    @Column(name = "gender", nullable = false, length = 6)
    private String gender;

    @Basic
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @ManyToOne
    @JoinColumn(name = "d_id", referencedColumnName = "id", nullable = false)
    private Department department;

    @Basic
    @Column(name = "hiredate", nullable = false)
    private Date hiredate;

    @Basic
    @Column(name = "dismissal", nullable = true)
    private Date dismissal;

    @ManyToOne
    @JoinColumn(name = "j_id", referencedColumnName = "id", nullable = false)
    private Job job;

    @Basic
    @Column(name = "salary", nullable = false)
    private int salary;

    @Basic
    @Column(name = "chief", nullable = false)
    private boolean chief;

    @Override
    public Integer getId() {
        return id;
    }
}
