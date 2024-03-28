package com.batch.demo;

import com.batch.demo.config.JobRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/run")
public class ControllerTeste {

    @Autowired
    JobRunner jobRunner;

    @GetMapping()
    public void runJob(String a){
        jobRunner.runJob();
    }
}
