package com.example.basic_scheduler;

import java.util.List;

public interface JobDetailsService {
	List<JobDetails> findAll();
	JobDetails save(JobDetails jobDetails);
}
