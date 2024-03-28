package com.batch.demo.config;

import com.batch.demo.jobs.ExportUsersJob;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobRunner {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job exportUserJob;

    public void runJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(exportUserJob, jobParameters);

            System.out.println("Status do Job: " + jobExecution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
