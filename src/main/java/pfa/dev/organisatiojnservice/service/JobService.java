package pfa.dev.organisatiojnservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pfa.dev.organisatiojnservice.dto.JobDto;
import pfa.dev.organisatiojnservice.entities.Job;

public interface JobService {
    Page<JobDto> getAllJobs(Pageable pageable);
    JobDto aadJob(JobDto jobDto, Long depId);
    Job getJobById(Long id);
    JobDto updateJob(Long id, JobDto jobDto);
    void deleteJob(Long id);
    JobDto getJobByTitle(String title);
    JobDto addJobToDepartment(Long jobId, Long departmentId);
    
}
