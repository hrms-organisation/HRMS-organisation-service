package pfa.dev.organisatiojnservice.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;

    private String name;

    private String code;


    private List<Long> jobId;

}
