package com.example.altarix.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/jobs")
@RestController
public class JobRestController {

    private final IJobRepository IJobRepository;

    @Autowired
    public JobRestController(IJobRepository IJobRepository) {
        this.IJobRepository = IJobRepository;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public List<Job> getAllJobs() {
        List<Job> employees = new ArrayList<>();
        IJobRepository.findAll()
                .forEach(employees::add);
        return employees;
    }
}
