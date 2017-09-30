package com.example.altarix.department;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IDepartmentRepository extends PagingAndSortingRepository<Department, Integer>{

    @Query("select d from Department  d where d.name = :name")
    Department findByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("update Department d set d.name=:name where d.id=:id ")
    void updateName(@Param("name") String name, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update Department d set d.departmentMaster=:departmentMaster where d.id=:id ")
    void updateMaster(@Param("departmentMaster") Department d, @Param("id") Integer id);
}
