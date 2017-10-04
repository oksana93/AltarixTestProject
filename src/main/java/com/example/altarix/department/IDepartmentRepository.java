package com.example.altarix.department;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDepartmentRepository extends PagingAndSortingRepository<Department, Integer>{

    Department findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Department d SET d=:department WHERE d.id=:id")
    void updateName(@Param("department") Department department, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Department d SET d=:department WHERE d.id=:id")
    void updateMaster(@Param("department") Department department, @Param("id") Integer id);

    @Query("SELECT d.name FROM Department d WHERE d.name LIKE CONCAT('%',:name,'%')")
    List<String> findByPartName(@Param("name")String name);

}
