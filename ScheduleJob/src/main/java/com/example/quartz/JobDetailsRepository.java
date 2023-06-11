package com.example.quartz;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic_scheduler.JobDetails;

public interface JobDetailsRepository extends JpaRepository<JobDetails, Integer>{

}
