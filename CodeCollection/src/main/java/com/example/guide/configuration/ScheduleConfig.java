package com.example.guide.configuration;

import com.example.guide.entity.TestEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
         taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor(){
        return Executors.newScheduledThreadPool(5);
    }

//    @Bean
//    @Scope(scopeName = "")
//    public TestEntity test(){
//        return
//    }
}
