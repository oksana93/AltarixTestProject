package com.example.altarix.timer;

import com.example.altarix.department.Department;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "timer_department_salary")
public class TimerTable {
    @Id
    @GeneratedValue
    private Integer id;

    @Basic
    @Generated(GenerationTime.ALWAYS)
    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "d_id", referencedColumnName = "id", nullable = false)
    private Department department;

    @Basic
    @Column(name = "salary")
    private Integer salary;


}
