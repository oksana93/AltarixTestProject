package com.example.altarix.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/departments")
@RestController
public class DepartmentRestController {

    private final IDepartmentRepository departmentRepository;

    @Autowired
    public DepartmentRestController(IDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @RequestMapping(value = "/getAllDepartments", method = RequestMethod.GET)
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll()
                .forEach(departments::add);
        return departments;
    }

    @RequestMapping(value = "/getAllMastersDepartments", method = RequestMethod.GET)
    public List<Department> getAllMastersDepartments() {
        List<Department> departments = new ArrayList<>();

        return departments;
    }

    @RequestMapping(value = "/createUniqueDepartment", method = RequestMethod.POST)
    public void createUniqueDepartment(@RequestBody Department department) {
        Department department1Duplicate = departmentRepository.findByName(department.getName());
        if (department1Duplicate != null)
            throw new RuntimeException("Found duplicate during exception");
        departmentRepository.save(department);
    }

    @RequestMapping(value = "/updateUniqueDepartmentName", method = RequestMethod.POST)
    public void updateUniqueDepartmentName(@RequestBody Department department) {
        Department department1Duplicate = departmentRepository.findByName(department.getName());
        if (department1Duplicate != null)
            throw new RuntimeException("Found duplicate during exception");
        departmentRepository.updateName(department.getName(), department.getId());
    }

    @RequestMapping(value = "/updateDepartmentMaster", method = RequestMethod.POST)
    public void updateDepartmentMaster(@RequestBody Department department) {
        Department departmentMasterExist = departmentRepository.findOne(department.getDepartmentMaster().getId());
        if (departmentMasterExist == null)
            throw new RuntimeException("The master (department) does not exist!");
        departmentRepository.updateMaster(department.getDepartmentMaster(), department.getId());
    }

    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.DELETE)
    public void deleteDepartment(@RequestBody Department department){
        Department departmentExist = departmentRepository.findOne(department.getId());
        if (departmentExist == null)
            throw new RuntimeException("The department does not exist!");
        departmentRepository.delete(department.getId());
    }


}
