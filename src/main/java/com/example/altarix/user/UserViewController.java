package com.example.altarix.user;

import com.example.altarix.department.DepartmentRestController;
import com.example.altarix.dto.InfoDTORestController;
import com.example.altarix.employee.EmployeeRestController;
import com.example.altarix.job.JobRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserViewController {

    @Autowired
    DepartmentRestController departmentRestController;
    @Autowired
    EmployeeRestController employeeRestController;
    @Autowired
    JobRestController jobRestController;
    @Autowired
    InfoDTORestController infoDTORestController;

    @RequestMapping("/user")
    public String user(Model model) {
        model.addAttribute("departments", departmentRestController.getAllDepartments());
        model.addAttribute("employees", employeeRestController.getAllEmployees());
        model.addAttribute("jobs", jobRestController.getAllJobs());
        return "user";
    }


}
