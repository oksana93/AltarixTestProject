package com.example.altarix.department;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IDepartmentRepository extends PagingAndSortingRepository<Department, Integer>{

    @Query("select department from Department  department where department.name = :name")
    Department findByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("update Department department set department.name=:name where department.id=:id ")
    void updateName(@Param("name") String name, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update Department department set department.departmentMaster=:departmentMaster where department.id=:id ")
    void updateMaster(@Param("departmentMaster") Department department, @Param("id") Integer id);
}
