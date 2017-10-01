package com.example.altarix.employee;

import com.example.altarix.department.Department;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Integer>{

    List<Employee> findEmployeesByDepartment(Department department);


}
