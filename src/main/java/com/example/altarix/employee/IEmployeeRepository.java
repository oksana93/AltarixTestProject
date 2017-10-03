package com.example.altarix.employee;

import com.example.altarix.department.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Integer>{

    List<Employee> findEmployeesByDepartment(Department department);

    @Query("SELECT e FROM Employee e WHERE e.department = :department AND e.chief = true")
    Employee getChiefEmployeeByDepartment(@Param("department") Department department);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department = :department")
    Integer getCountEmployeesByDepartment(@Param("department") Department department);

    @Query("SELECT sum(e.salary) FROM Employee e WHERE e.department=:department")
    Integer getSumSalaryByDepartment(@Param("department") Department department);

    @Query("SELECT e FROM Employee e WHERE e.department=:department AND e.chief = true")
    Employee getEmployeeChiefByDepartmentId(@Param("department") Department department);
}
