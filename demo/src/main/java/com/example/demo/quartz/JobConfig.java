package com.example.demo.quartz;

import org.quartz.*;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
public class JobConfig {

    @Bean
    @QuartzDataSource
    public DataSource qdataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=FormQuartz;encrypt=true;trustServerCertificate=true");
        return dataSource;
    }


    @Bean
    public JobDetail springJob1Detail() {
        return JobBuilder.newJob(CheckForm.class)
                .withIdentity("springJob1Detail")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger springJob1Trigger() {
        return TriggerBuilder.newTrigger()
                .forJob("springJob1Detail")
                .startNow()
                .withSchedule(cronSchedule("0 0 0 * * ?"))
                .build();
    }
}
