package com.example.quartz;

import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import com.example.basic_scheduler.JobDetails;

@Service
public class CustomScheduledJob {
	
	private final JobDetailsRepository jobDetailsRepository;
	
	public CustomScheduledJob(JobDetailsRepository jobDetailsRepository) {
		this.jobDetailsRepository = jobDetailsRepository;
	}
	
	public void scheduledATask(JobDetails jobDetails) throws SchedulerException {
		JobDetail jobDetail = buildJobDetail(jobDetails);
		Trigger trigger = buildTrigger(jobDetails);
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	private JobDetail buildJobDetail(JobDetails jobDetails) {
		return JobBuilder.newJob(JobExecutor.class)
				.withIdentity(jobDetails.getJobId())
				.withDescription(jobDetails.getJobDescription())
				.storeDurably()
				.build();
		
	}

	private Trigger buildTrigger(JobDetails jobDetails) {
		return TriggerBuilder.newTrigger()
				.forJob(jobDetails.getJobId())
				.withIdentity(jobDetails.getJobId())
				.withDescription("Trigger for scheduled job: " + jobDetails.getJobId())
				.startAt(new Date())
				.withSchedule(CronScheduleBuilder.cronSchedule(jobDetails.getCronTime()))
				.build();
	}

	public void scheduledAllTask() {
		List<JobDetails> listJobDetails = jobDetailsRepository.findAll();
		for(JobDetails jobDetails : listJobDetails) {
			try {
				scheduledATask(jobDetails);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
