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

    private final JobRepository jobRepository;

    @Autowired
    public JobRestController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public List<Job> get() {
        List<Job> employees = new ArrayList<>();
        jobRepository.findAll()
                .forEach(employees::add);
        return employees;
    }
}
