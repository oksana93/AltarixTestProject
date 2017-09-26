package com.example.altarix.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/departments")
@RestController
public class DepartmentRestController {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentRestController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public List<Department> get() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll()
                .forEach(departments::add);
        return departments;
    }
}
