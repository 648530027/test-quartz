package com.ypw.quartz.scheduler;

import com.ypw.quartz.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class MyScheduler {
    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myjob", "testJob").build();
        SimpleTrigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();
        SchedulerFactory factory = new StdSchedulerFactory() ;
        Scheduler scheduler = factory.getScheduler();

        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
