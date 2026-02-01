package pfa.dev.organisatiojnservice.service;

import pfa.dev.organisatiojnservice.dto.JobDto;
import pfa.dev.organisatiojnservice.entities.Job;

public interface JobService {
    JobDto aadJob(JobDto jobDto, Long depId);
    Job getJobById(Long id);
    JobDto updateJob(Long id, JobDto jobDto);
    void deleteJob(Long id);
    JobDto getJobByTitle(String title);
    JobDto addJobToDepartment(Long jobId, Long departmentId);
    
}
