package pfa.dev.organisatiojnservice.mapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pfa.dev.organisatiojnservice.dto.JobDto;
import pfa.dev.organisatiojnservice.entities.Job;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobDto toDto(Job job);

    Job toEntity(JobDto jobDto);
}
