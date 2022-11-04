package com.example.demo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class DeleteUser implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteUser.class);

    @Override
    public void execute(JobExecutionContext context){

        LOG.info("ez");
    }
}
