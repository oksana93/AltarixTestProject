package com.example.altarix.department;

import com.example.altarix.employee.Employee;
import com.example.altarix.employee.IEmployeeRepository;
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
    private final IEmployeeRepository employeeRepository;

    @Autowired
    public DepartmentRestController(IDepartmentRepository departmentRepository, IEmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    // select all departments
    @RequestMapping(value = "/getAllDepartments", method = RequestMethod.GET)
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll()
                .forEach(departments::add);
        return departments;
    }

    // D1
    @RequestMapping(value = "/createUniqueDepartment", method = RequestMethod.POST)
    public void insertUniqueDepartment(@RequestBody Department department) {
        Department department1Duplicate = departmentRepository.findByName(department.getName());
        if (department1Duplicate != null)
            throw new RuntimeException("Found duplicate during exception");
        departmentRepository.save(department);
    }

    // D2
    @RequestMapping(value = "/updateUniqueDepartmentName", method = RequestMethod.POST)
    public void updateUniqueDepartmentName(@RequestBody Department department) {
        Department department1Duplicate = departmentRepository.findByName(department.getName());
        if (department1Duplicate != null)
            throw new RuntimeException("Found duplicate during exception");
        Department oldDepartment = departmentRepository.findOne(department.getId());
        oldDepartment.setName(department.getName());
        departmentRepository.updateName(oldDepartment, department.getId());
    }

    // D7 Чтобы не возникали зацикливания, будем действовать как при удалении - ветки перемещать на родителя.
    @RequestMapping(value = "/updateDepartmentMaster", method = RequestMethod.POST)
    public void updateDepartmentMaster(@RequestBody Department department) {
        if (department.getId().equals(department.getDepartmentMaster().getId()))
            throw new RuntimeException("The department could not be its master!");
        Department oldDepartment = departmentRepository.findOne(department.getId());
        if (oldDepartment.getDepartmentMaster() == null)
            throw new RuntimeException("Change the main department (root) is not a valid");
        changeAssociationBranchesAndMaster(oldDepartment);
        oldDepartment.setDepartmentMaster(department.getDepartmentMaster());
        departmentRepository.updateMaster(oldDepartment, oldDepartment.getId());
    }

    // D3 -> D7
    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.DELETE)
    public void deleteDepartment(@RequestBody Department department){
        Department oldDepartment = departmentRepository.findOne(department.getId());
        if (oldDepartment == null)
            throw new RuntimeException("The department does not exist");
        if (oldDepartment.getDepartmentMaster() == null)
            throw new RuntimeException("Delete the main department (root) is not a valid");
        List<Employee> employees = employeeRepository.findEmployeesByDepartment(department);
        if (employees != null && employees.size() != 0)
            throw new RuntimeException("The department contains employees!");
        changeAssociationBranchesAndMaster(oldDepartment);
        departmentRepository.delete(oldDepartment.getId());
    }

    // Меняет отношение branches и master - сам узел department выпадает из отношения
    private void changeAssociationBranchesAndMaster(Department department) {
        Department departmentMaster = department.getDepartmentMaster();
        List<Department> departmentsBranch = new ArrayList<>(department.getDepartmentsBranch());
        departmentsBranch.forEach(branch -> {
            branch.setDepartmentMaster(departmentMaster);
            departmentRepository.updateMaster(branch, branch.getId());
        });
    }

//    @RequestMapping(value = "/getInfoToDepartment", method = RequestMethod.GET)
//    public Department getInfoToDepartment(@RequestBody Department department) {
//        Department departmentToDTO = departmentRepository.findByName(department.getName());
//        Employee chiefToDTO = employeeRepository.getChiefEmployeeByDepartment(departmentToDTO);
//        int employeeCountToDTO = employeeRepository.getCountEmployeesByDepartment(departmentToDTO);
//        InfoDTO infoDTO = new InfoDTO();
//        infoDTO.setDepartmentName(departmentToDTO.getName());
//        infoDTO.setFLPChief(chiefToDTO.getFirstname() + " " +
//                chiefToDTO.getLastname() + " " + chiefToDTO.getPatronymic());
//        infoDTO.setEmployeesCount(employeeCountToDTO);
//        return departmentToDTO;
//    }



}
