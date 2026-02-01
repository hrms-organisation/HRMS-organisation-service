package pfa.dev.organisatiojnservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.dev.organisatiojnservice.entities.Job;


public interface JobRepository extends JpaRepository<Job, Long> {
    Job findByTitle(String title);
    boolean existsJobByTitle(String title   );
}
