package com.example.altarix.timer;

import com.example.altarix.department.IDepartmentRepository;
import com.example.altarix.employee.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class TimerComponent {

    private final IDepartmentRepository departmentRepository;
    private final IEmployeeRepository employeeRepository;
    private final ITimerTableRepository timerTableRepository;

    private Timer timer;
    private TimerTask timerTask;

    @Autowired
    public TimerComponent(IDepartmentRepository departmentRepository, IEmployeeRepository employeeRepository, ITimerTableRepository timerTableRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.timerTableRepository = timerTableRepository;
    }

    @PostConstruct
    public void initTimer() {
        timerTask = new DepartmentTimerTask(departmentRepository, employeeRepository, timerTableRepository);
        timer = new Timer(true);
        // будем запускать каждых 5 минут - 300 секунд (300 * 1000 миллисекунд)
        timer.scheduleAtFixedRate(timerTask, 0, 300 * 1000);
    }
}
