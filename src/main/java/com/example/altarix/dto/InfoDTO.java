package com.example.altarix.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class InfoDTO {

    private String departmentName;

    private Date departmentDate;

    private String FLPChief; //firstname lastname patronymic

    private int employeesCount;
}
