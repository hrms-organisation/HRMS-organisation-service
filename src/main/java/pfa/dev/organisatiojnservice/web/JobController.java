package pfa.dev.organisatiojnservice.web;

import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.dev.organisatiojnservice.dto.JobDto;
import pfa.dev.organisatiojnservice.entities.Job;
import pfa.dev.organisatiojnservice.service.JobService;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class JobController {
    private final JobService jobService;



  @GetMapping("/get/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable  Long id){
        return ResponseEntity.ok(jobService.getJobById(id));

    }
    @GetMapping("/get/title/{title}")
    public ResponseEntity<JobDto> getJobByTitle(@PathVariable String title){
        return ResponseEntity.ok(jobService.getJobByTitle(title));
    }
    @PostMapping("/add/{jobId}/{departmentId}")
    public ResponseEntity<JobDto> addJobToDepartment( @PathVariable Long jobId, @PathVariable  Long departmentId){
        return ResponseEntity.ok(jobService.addJobToDepartment(jobId,departmentId));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable  Long id,@RequestBody JobDto jobDto){
        return ResponseEntity.ok(jobService.updateJob(id,jobDto));
    }
    @PostMapping("/addJob/{depId}")
    public  ResponseEntity<JobDto> aadJob(@RequestBody JobDto jobDto ,@PathVariable  Long depId){
      return ResponseEntity.ok(jobService.aadJob(jobDto , depId));
    }


}
