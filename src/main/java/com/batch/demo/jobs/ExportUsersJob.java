package com.batch.demo.jobs;

import com.batch.demo.JobsListener.ExportUsersListener;
import com.batch.demo.steps.UsersStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class ExportUsersJob {

    @Autowired
    private ExportUsersListener jobCompletionNotificationListener;


    @Autowired
    private DataSource dataSource;



    @Bean
    public Job exportUserJob() throws Exception {
        return new JobBuilder("exportUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobCompletionNotificationListener)
                .flow(new UsersStep(stepBuilderFactory()).step1())
                .end()
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        return new ResourcelessTransactionManager(); // Use o gerenciador de transações apropriado para seu banco de dados
    }

    @Bean
    public JobRepository jobRepository(PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(transactionManager);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    StepBuilderFactory stepBuilderFactory() throws Exception {
        return new StepBuilderFactory(jobRepository(transactionManager()));
    }
}
