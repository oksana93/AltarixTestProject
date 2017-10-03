package com.example.altarix.timer;

import com.example.altarix.department.IDepartmentRepository;
import com.example.altarix.employee.IEmployeeRepository;

import java.util.TimerTask;

public class DepartmentTimerTask extends TimerTask {


    private final IDepartmentRepository departmentRepository;
    private final IEmployeeRepository employeeRepository;
    private final ITimerTableRepository timerTableRepository;

    public DepartmentTimerTask(IDepartmentRepository departmentRepository, IEmployeeRepository employeeRepository, ITimerTableRepository timerTableRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.timerTableRepository = timerTableRepository;
    }

    @Override
    public void run() {
        departmentRepository.findAll().forEach(department -> {
            Integer salary = employeeRepository.getSumSalaryByDepartment(department);
            TimerTable timerTable = new TimerTable();
            timerTable.setDepartment(department);
            timerTable.setSalary(salary);
            timerTableRepository.save(timerTable);
        });
    }
}
