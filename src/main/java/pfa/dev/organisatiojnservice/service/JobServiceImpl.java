package pfa.dev.organisatiojnservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pfa.dev.organisatiojnservice.dto.JobDto;
import pfa.dev.organisatiojnservice.dto.JobSummaryResponse;
import pfa.dev.organisatiojnservice.entities.Department;
import pfa.dev.organisatiojnservice.entities.Job;
import pfa.dev.organisatiojnservice.mapper.JobMapper;
import pfa.dev.organisatiojnservice.repositories.DepartmentRepository;
import pfa.dev.organisatiojnservice.repositories.JobRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor

public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;
    private final JobMapper jobMapper;


    @Override
    public Page<JobDto> getAllJobs(Pageable pageable) {
        return jobRepository.findAll(pageable).map(jobMapper::toDto);
    }

    @Transactional
    @Override
    public JobDto aadJob(JobDto jobDto , Long depId) {
        Department department = departmentRepository.findById(depId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        if (jobRepository.existsJobByTitle(jobDto.getTitle())) {
            throw new IllegalArgumentException("Job with code " + jobDto.getTitle() + " already exists");
        }

        Job job = jobMapper.toEntity(jobDto);
        job = jobRepository.save(job);

        if (department.getJobs() == null) {
            department.setJobs(new ArrayList<>());
        }
        department.getJobs().add(job);
        departmentRepository.save(department);

        return jobMapper.toDto(job);
    }

    @Override
    public Job getJobById(Long id) {
        return existJob(id);
    }

    @Override
    public JobSummaryResponse getJobSummaryById(Long id) {
        Job job = existJob(id);
        return new JobSummaryResponse(job.getId(), job.getTitle());
    }

    @Override
    public JobDto updateJob(Long id, JobDto jobDto) {
        existJob(id);
        Job job = jobRepository.save(jobMapper.toEntity(jobDto));

        return jobMapper.toDto(job);
    }

    @Override
    public void deleteJob(Long id) {
        existJob(id);
        jobRepository.deleteById(id);

    }

    @Override
    public JobDto getJobByTitle(String title) {
        return jobMapper.toDto(jobRepository.findByTitle(title));
    }
    @Transactional
    @Override
    public JobDto addJobToDepartment(Long jobId, Long departmentId) {
        Department dep = existDepartment(departmentId);
        Job job = existJob(jobId);
        if(dep!= null && job!=null){
            dep.getJobs().add(job);
            departmentRepository.save(dep);
            return jobMapper.toDto(job);
        }


        return null;
    }
    private Job existJob(Long id){
        return jobRepository.findById(id).orElseThrow(()->new RuntimeException("Job not found with id: "+id));
    }
    private Department existDepartment(Long departmentId){
        return departmentRepository.findById(departmentId).orElseThrow(()->
                new RuntimeException("Department not found with id: "+departmentId));
    }
}
