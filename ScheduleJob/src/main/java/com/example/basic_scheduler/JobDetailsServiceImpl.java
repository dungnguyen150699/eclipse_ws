package com.example.basic_scheduler;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class JobDetailsServiceImpl implements JobDetailsService{
	
	private final JobDetailsRepository jobDetailsRepository;
	
	public JobDetailsServiceImpl(JobDetailsRepository jobDetailsRepository) {
		this.jobDetailsRepository = jobDetailsRepository;
	}

	@Override
	public List<JobDetails> findAll() {
		// TODO Auto-generated method stub
		return jobDetailsRepository.findAll();
	}

	@Override
	public JobDetails save(JobDetails jobDetails) {
		// TODO Auto-generated method stub
		return jobDetailsRepository.save(jobDetails);
	}
	
}
