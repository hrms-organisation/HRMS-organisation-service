package pfa.dev.organisatiojnservice.web;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.dev.organisatiojnservice.dto.JobDto;
import pfa.dev.organisatiojnservice.dto.JobSummaryResponse;
import pfa.dev.organisatiojnservice.entities.Job;
import pfa.dev.organisatiojnservice.service.JobService;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('HR', 'EMPLOYEE')")
public class JobController {
    private final JobService jobService;

  @GetMapping("/list")
    public ResponseEntity<Page<JobDto>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(jobService.getAllJobs(PageRequest.of(page, size)));
    }

  @GetMapping("/get/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable  Long id){
        return ResponseEntity.ok(jobService.getJobById(id));

    }
    @GetMapping("/{id}/summary")
    public ResponseEntity<JobSummaryResponse> getJobSummaryById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobSummaryById(id));
    }
    @GetMapping("/get/title/{title}")
    public ResponseEntity<JobDto> getJobByTitle(@PathVariable String title){
        return ResponseEntity.ok(jobService.getJobByTitle(title));
    }
    @PostMapping("/add/{jobId}/{departmentId}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<JobDto> addJobToDepartment( @PathVariable Long jobId, @PathVariable  Long departmentId){
        return ResponseEntity.ok(jobService.addJobToDepartment(jobId,departmentId));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('HR')")
    public void deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<JobDto> updateJob(@PathVariable  Long id,@RequestBody JobDto jobDto){
        return ResponseEntity.ok(jobService.updateJob(id,jobDto));
    }
    @PostMapping("/addJob/{depId}")
    @PreAuthorize("hasRole('HR')")
    public  ResponseEntity<JobDto> aadJob(@RequestBody JobDto jobDto ,@PathVariable  Long depId){
      return ResponseEntity.ok(jobService.aadJob(jobDto , depId));
    }


}
