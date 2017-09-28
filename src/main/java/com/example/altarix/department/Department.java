package com.example.altarix.department;

import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;


@Data
@Entity
@Table(name = "departments")
public class Department implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Basic
    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private Department departmentMaster;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departmentMaster")
    private Collection<Department> departmentsBranch;

    @Override
    public Integer getId() {
        return id;
    }
}

