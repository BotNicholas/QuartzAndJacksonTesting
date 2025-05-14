package org.botnicholas.projects.democron.services.model;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class SoutJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(ZonedDateTime.now() + " : " + jobExecutionContext.getMergedJobDataMap().get("payload"));
    }
}
