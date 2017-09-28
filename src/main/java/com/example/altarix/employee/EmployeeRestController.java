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

    private final com.example.altarix.employee.IEmployeeRepository IEmployeeRepository;

    @Autowired
    public EmployeeRestController(com.example.altarix.employee.IEmployeeRepository IEmployeeRepository) {
        this.IEmployeeRepository = IEmployeeRepository;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        IEmployeeRepository.findAll()
                .forEach(employees::add);
        return employees;
    }
}
