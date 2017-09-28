package com.example.altarix.department;

import java.util.Collection;

public class DepartmentsDTO {

    private Collection<Department> departments;

    public DepartmentsDTO() {}

    public DepartmentsDTO(Collection<Department> departments) {
        this.departments = departments;
    }

    public Collection<Department> getDepartments() {
        return departments;
    }
}
