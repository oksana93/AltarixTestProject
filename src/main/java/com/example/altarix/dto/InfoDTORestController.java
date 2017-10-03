package com.example.altarix.dto;

import com.example.altarix.department.Department;
import com.example.altarix.department.IDepartmentRepository;
import com.example.altarix.employee.Employee;
import com.example.altarix.employee.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/info")
@RestController
public class InfoDTORestController {

    private final IDepartmentRepository departmentRepository;
    private final IEmployeeRepository employeeRepository;

    @Autowired
    public InfoDTORestController(IDepartmentRepository departmentRepository, IEmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    // D5, D6, D8, D9, D10
    @RequestMapping(value = "/getInfoToDepartment", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public InfoDTO getInfoToDepartment(@RequestBody Department department) {
        Department departmentToDTO = departmentRepository.findOne(department.getId());
        if (departmentToDTO == null)
            throw new RuntimeException("The department does not exist");

        List<String> departmentsBranchesFirstLevel = new ArrayList<>();
        departmentToDTO.getDepartmentsBranch().forEach(branch -> departmentsBranchesFirstLevel.add(branch.getName()));

        List<String> departmentsBranchesAllLevel = new ArrayList<>();
        departmentsBranchesAllLevel = getAllBranches(departmentToDTO, departmentsBranchesAllLevel);

        List<String> departmentsAllMasters = new ArrayList<>();
        departmentsAllMasters = getAllMasters(departmentToDTO, departmentsAllMasters);

        Employee chiefToDTO = employeeRepository.getChiefEmployeeByDepartment(departmentToDTO);

        Integer employeeCountToDTO = employeeRepository.getCountEmployeesByDepartment(departmentToDTO);

        Integer sumSalary = employeeRepository.getSumSalaryByDepartment(departmentToDTO);

        InfoDTO infoDTO = new InfoDTO();
        infoDTO.setDepartmentName(departmentToDTO.getName());
        infoDTO.setDepartmentDate(departmentToDTO.getDate());
        infoDTO.setChief(chiefToDTO != null ? chiefToDTO.getFirstname() + " " +
                chiefToDTO.getLastname() + " " + chiefToDTO.getPatronymic() : "-");
        infoDTO.setEmployeesCount(employeeCountToDTO != null ? employeeCountToDTO : 0);
        infoDTO.setSumSalary(sumSalary != null ? sumSalary : 0);
        infoDTO.setBranchesFirstLevel(departmentsBranchesFirstLevel);
        infoDTO.setBranchesAllLevel(departmentsBranchesAllLevel);
        infoDTO.setMastersAllLevel(departmentsAllMasters);
        return infoDTO;
    }

    private List<String> getAllBranches(Department department, List<String> departments) {
        List<Department> departmentsBranches = (List<Department>) department.getDepartmentsBranch();
        departmentsBranches.forEach(branch -> {
            departments.add(branch.getName());
            getAllBranches(branch, departments);
        });
        return departments;
    }


    private List<String> getAllMasters(Department department, List<String> departments) {
        Department departmentsMaster = department.getDepartmentMaster();
        if (departmentsMaster != null) {
            departments.add(departmentsMaster.getName());
            getAllMasters(departmentsMaster, departments);
        }
        return departments;
    }

}
