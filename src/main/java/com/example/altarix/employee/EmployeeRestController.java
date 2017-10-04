package com.example.altarix.employee;

import com.example.altarix.department.Department;
import com.example.altarix.department.IDepartmentRepository;
import com.example.altarix.dto.InfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/employees")
@RestController
public class EmployeeRestController {

    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;

    @Autowired
    public EmployeeRestController(IEmployeeRepository employeeRepository, IDepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll()
                .forEach(employees::add);
        return employees;
    }

    // D1
    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
    public void insertEmployee(@RequestBody Employee employee) {
        // будем считать, что начальник один -> проверка, я-я ли начальником. Если да и есть в департаменте начальник, chief = false;
        System.out.println(employee.isChief());
        if (employee.isChief() == true)
            if (employeeRepository.getEmployeeChiefByDepartmentId(employee.getDepartment()) != null) {
                employee.setChief(false);
                employeeRepository.save(employee);
                throw new RuntimeException("The Chief of this department is existed! Employee is created (not chief)");
            }
        employeeRepository.save(employee);
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    public void deleteEmployee(@RequestBody Employee employee) {
        Employee oldEmployee = employeeRepository.findOne(employee.getId());
        if (oldEmployee == null)
            throw new RuntimeException("The employee does not exist");
        employeeRepository.delete(employee);
    }

    @RequestMapping(value = "/transferEmployee", method = RequestMethod.POST)
    public void transferEmployee(@RequestBody Employee employee) {
        Employee oldEmployee = employeeRepository.findOne(employee.getId());
        if (oldEmployee == null)
            throw new RuntimeException("The employee does not exist");
        oldEmployee.setDepartment(employee.getDepartment());
        employeeRepository.updateEmployee(employee, employee.getId());
    }

    @RequestMapping(value = "/transferEmployees", method = RequestMethod.POST)
    public void transferEmployees(@RequestBody Department department) {
        department = departmentRepository.findOne(department.getId());
        employeeRepository.transferEmployees(department);
    }

    @RequestMapping(value = "/chiefByEmployeeId", method = RequestMethod.POST)
    public InfoDTO chiefByEmployeeId(@RequestBody Employee employee) {
        Employee employeeChief = employeeRepository.getChiefEmployeeByEmployeeDepartment(employee.getId());
        InfoDTO infoDTO = new InfoDTO();
        if (employeeChief != null) {
            infoDTO.setChief(employeeChief.getFirstname() + " " + employeeChief.getLastname() + " " + employeeChief.getPatronymic());
            infoDTO.setDepartmentName(
                    employeeChief.getDepartment()
                            .getName());
        } else {
            employee = employeeRepository.findOne(employee.getId());
            infoDTO.setChief("No authority - it's anarchy");
            infoDTO.setDepartmentName(
                    employee.getDepartment()
                            .getName());
        }
        return infoDTO;
    }
}
