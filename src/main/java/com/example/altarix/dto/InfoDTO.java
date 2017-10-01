package com.example.altarix.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class InfoDTO {

    private String departmentName;

    private Date departmentDate;

    private String chief; //firstname lastname patronymic

    private Integer employeesCount;

    private Long sumSalary;

    private List<String> branchesFirstLevel;

    private List<String> branchesAllLevel;

    private List<String> mastersAllLevel;
}
