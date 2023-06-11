package com.example.basic_scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
@RestController
@RequestMapping("/jobs")
public class CronjobController {
	private Map<String, ScheduledFuture<?>> jobMaps = new HashMap<>();
	
	private final JobDetailsService jobDetailsService;
	private final TaskScheduler taskScheduler;
	
	public CronjobController(JobDetailsService jobDetailsService, TaskScheduler taskScheduler) {
		this.jobDetailsService = jobDetailsService;
		this.taskScheduler = taskScheduler;
	}
	
	@PostConstruct
	public void initAllJobs() {
		List<JobDetails> jobDetailsList = jobDetailsService.findAll();
		
		for(JobDetails jobDetails : jobDetailsList) {
			ScheduledFuture<?> scheduledTask = taskScheduler.schedule(() -> {
				System.out.println("JobKey: " + jobDetails.getJobId());
				System.out.println("JobDescription: " + jobDetails.getJobDescription());
				System.out.println("CronTime: " + jobDetails.getCronTime());
			}, 
			new CronTrigger(jobDetails.getCronTime()));
			
			jobMaps.put(jobDetails.getJobId(), scheduledTask);
		}
	}
	
	@PostMapping("save")
	public void saveJobDetailsSetting(@RequestBody JobDetails jobDetails) {
		JobDetails savedJobDetails = jobDetailsService.save(jobDetails);
		ScheduledFuture<?> scheduledTask = taskScheduler.schedule(() -> {
			System.out.println("JobKey: " + savedJobDetails.getJobId());
			System.out.println("JobDescription: " + savedJobDetails.getJobDescription());
			System.out.println("CronTime: " + savedJobDetails.getCronTime());
		}, 
		new CronTrigger(savedJobDetails.getCronTime()));
		if(jobMaps.containsKey(savedJobDetails.getJobId())) {
			ScheduledFuture<?> existScheduledTask = jobMaps.get(savedJobDetails.getJobId());
			existScheduledTask.cancel(true);
		}
		System.out.println("Cancel old scheduled task!");
		jobMaps.put(savedJobDetails.getJobId(), scheduledTask);
	}
	
}
