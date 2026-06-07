package pfa.dev.organisatiojnservice.mapper;

import jakarta.persistence.Id;
import org.mapstruct.*;
import pfa.dev.organisatiojnservice.dto.DepartmentDto;
import pfa.dev.organisatiojnservice.entities.Department;
import pfa.dev.organisatiojnservice.entities.Job;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(target = "jobId", expression = "java(department.getJobs() != null ? department.getJobs().stream().map(job -> job.getId()).collect(java.util.stream.Collectors.toList()) : null)")
    DepartmentDto toDto(Department department);

    @InheritInverseConfiguration
    @Mapping(target = "jobs", ignore = true)
    Department toEntity(DepartmentDto dto);

}
