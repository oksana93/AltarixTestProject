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
    private Integer e_id;

    @Basic
    @Column(name = "e_firstname", nullable = false, length = 60)
    private String e_firstname;

    @Basic
    @Column(name = "e_lastname", nullable = false, length = 60)
    private String e_lastname;

    @Basic
    @Column(name = "e_patronymic", nullable = false, length = 60)
    private String e_patronymic;

    @Basic
    @Column(name = "e_gender", nullable = false, length = 6)
    private String e_gender;

    @Basic
    @Column(name = "e_birthdate", nullable = false)
    private Date e_birthdate;

    @ManyToOne
    @JoinColumn(name = "d_id", referencedColumnName = "d_id", nullable = false)
    private Department e_department;

    @Basic
    @Column(name = "e_hiredate", nullable = false)
    private Date e_hiredate;

    @Basic
    @Column(name = "e_dismissal", nullable = true)
    private Date e_dismissal;

    @ManyToOne
    @JoinColumn(name = "j_id", referencedColumnName = "j_id", nullable = false)
    private Job job;

    @Basic
    @Column(name = "e_salary", nullable = false)
    private int e_salary;

    @Basic
    @Column(name = "e_chief", nullable = false)
    private boolean e_chief;

    @Override
    public Integer getId() {
        return e_id;
    }
}
