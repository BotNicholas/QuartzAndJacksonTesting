package org.botnicholas.projects.democron.services;

import org.botnicholas.projects.democron.controllers.models.SchedulingRequest;
import org.botnicholas.projects.democron.services.model.SoutJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.quartz.TriggerBuilder.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

@Service
public class CronSchedulerService {
    private Scheduler quartzScheduler;

    @Autowired
    public CronSchedulerService(Scheduler quartzScheduler) {
        this.quartzScheduler = quartzScheduler;
    }

    public void scheduleCron(SchedulingRequest req) throws SchedulerException {

        System.out.println(req);
        //The date must be like 2019-03-28T10:15:30+01:00[Europe/Paris]
//        var zonedDateTime = ZonedDateTime.parse(req);
//        var zonedDateTime = ZonedDateTime.of(2019, 3, 28, 10, 15, 00, 00, ZoneId.of("Europe/Paris"));
//        var zonedDateTime = ZonedDateTime.of(req.getYear(), req.getMonth(), req.getDay(), Integer.parseInt(req.getTime().split(":")[0]), Integer.parseInt(req.getTime().split(":")[1]), 00, 00, ZoneId.of(req.getTimeZone()));


//        System.out.println("What is the time in Chisinau at " + req);
//        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()));
//
//        System.out.println("\n\nWhat is the time it " + zonedDateTime.getZone() + " when it's" + zonedDateTime.getHour() + " In Chisinau");
//        System.out.println(zonedDateTime.withZoneSameLocal(ZoneId.systemDefault()).withZoneSameInstant(zonedDateTime.getZone()));
//
//        System.out.println("\n\nDate: " + Date.from(zonedDateTime.toInstant()));

//        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).getHour());
//        System.out.println(zonedDateTime.toLocalTime());



        var jobDetail = JobBuilder.newJob(SoutJob.class)
                .withIdentity(String.format("%s-%s", "TimeJob", UUID.randomUUID().toString()))
                .usingJobData("payload", String.format("Time Job At %s in %s", req.getTime(), req.getTimeZone()))
                .storeDurably()
                .build();


        var cronExpression = String.format("0 %s %s ? * * *", req.getTime().split(":")[1], req.getTime().split(":")[0]);

        System.out.println("\nScheduled:");
        System.out.println("\tCron Expression: " + cronExpression);
        System.out.println("\tAt zone: " + TimeZone.getTimeZone(req.getTimeZone()));

        var trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.format("%s-%s", "TimeTrigger", UUID.randomUUID().toString()))
                .forJob(jobDetail)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression).inTimeZone(TimeZone.getTimeZone(req.getTimeZone())))
                .build();

        quartzScheduler.addJob(jobDetail, true);
        quartzScheduler.scheduleJob(trigger);




//        var dateTime = OffsetDateTime.from(ISO_OFFSET_DATE_TIME.parse(req));
//
//        var jobDetail = JobBuilder.newJob(SoutJob.class)
//                .withIdentity(String.format("%s-%s", "TimeJob", UUID.randomUUID().toString()))
//                .usingJobData("payload", UUID.randomUUID().toString())
//                .storeDurably()
//                .build();
//
//        Date date = Date.from(zonedDateTime.toInstant());
//
//        var trigger = TriggerBuilder.newTrigger()
//                .withIdentity(String.format("%s-%s", "TimeTrigger", UUID.randomUUID().toString()))
//                .forJob(jobDetail)
//                .startAt(date)
//                .build();
//
//        quartzScheduler.addJob(jobDetail, true);
//        quartzScheduler.scheduleJob(trigger);
    }
}
