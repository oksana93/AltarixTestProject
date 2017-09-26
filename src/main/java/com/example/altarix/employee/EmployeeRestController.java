package com.example.altarix.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/employees")
@RestController
public class EmployeeRestController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRestController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public List<Employee> get() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll()
                .forEach(employees::add);
        return employees;
    }
}
