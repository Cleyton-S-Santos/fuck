package com.batch.demo.steps;

import com.batch.demo.domain.UserDomain;
import com.batch.demo.processors.UserProcessor;
import com.batch.demo.reads.UserReader;
import com.batch.demo.writers.UserExcelItemWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class UsersStep {

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public UsersStep(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<UserDomain, UserDomain>chunk(10)
                .reader(userItemReader())
                .processor(userItemProcessor())
                .writer(userWriter())
                .build();
    }

    @Bean
    public ItemReader<UserDomain> userItemReader(){
        return new UserReader();
    }

    @Bean
    public UserProcessor userItemProcessor(){
        return new UserProcessor();
    }

    @Bean
    public UserExcelItemWriter userWriter(){
        return new UserExcelItemWriter();
    }

}
