package com.example.demo.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.example.demo.service.FormInfoService;

public class CheckForm extends QuartzJobBean {

    private FormInfoService formService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        formService.checkFormTime();
    }
}
