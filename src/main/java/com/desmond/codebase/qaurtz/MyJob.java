package com.desmond.codebase.qaurtz;

import java.io.Serializable;
import java.util.Date;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by Li.Xiaochuan on 17/8/4.
 */
public class MyJob implements Serializable, Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(new Date() + ": doging something.");
    }

    private static void print() {
        int[] a = new int[] {1,2,3,4,5};
        for(int i : a) {
            System.out.println(i);
            return;
        }
    }

    public static void main(String[] args) throws SchedulerException {
        print();

//        // 1. define the job and tie it to our MyJob class
//        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
//                .withIdentity("job1", "group1")
//                .build();
//
//        // 2. trigger the job to run now, and the repeat every 10 seconds
//        SimpleTrigger trigger = newTrigger()
//                .withIdentity("trigger1", "group1")
//                .startNow()
//                .withSchedule(
//                        simpleSchedule()
//                                .withIntervalInSeconds(10)
//                                .repeatForever())
//                .build();
//
//        // 3. tell quartz to schedule the job using our trigger
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        scheduler.scheduleJob(jobDetail, trigger);
//
//        scheduler.start();

    }

}
