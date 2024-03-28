package com.batch.demo.JobsListener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class ExportUsersListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job Iniciado!");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Job Concluído!");
    }
}
