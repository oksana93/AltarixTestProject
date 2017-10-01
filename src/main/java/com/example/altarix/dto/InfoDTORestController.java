package com.example.altarix.dto;

import com.example.altarix.department.IDepartmentRepository;
import com.example.altarix.employee.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @RequestMapping(value = "/getInfoToDepartment", method = RequestMethod.GET)
//    public InfoDTO getInfoToDepartment(@RequestBody Department department) {
//        Department departmentToDTO = departmentRepository.findByName(department.getName());
//        Employee chiefToDTO = employeeRepository.getChiefEmployeeByDepartment(departmentToDTO);
//        int employeeCountToDTO = employeeRepository.getCountEmployeesByDepartment(departmentToDTO);
//        InfoDTO infoDTO = new InfoDTO();
//        infoDTO.setDepartmentName(departmentToDTO.getName());
//        infoDTO.setFLPChief(chiefToDTO.getFirstname() + " " +
//                chiefToDTO.getLastname() + " " + chiefToDTO.getPatronymic());
//        infoDTO.setEmployeesCount(employeeCountToDTO);
//        return infoDTO;
//    }


}
