package pfa.dev.organisatiojnservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pfa.dev.organisatiojnservice.dto.DepartmentSummaryResponse;
import pfa.dev.organisatiojnservice.dto.DepartmentDto;
import pfa.dev.organisatiojnservice.entities.Department;

public interface DepartmentService {
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    Department getDepartmentById(Long id);
    DepartmentSummaryResponse getDepartmentSummaryById(Long id);
    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);
    void deleteDepartment(Long id);
    Page<DepartmentDto> getAllDepartments(Pageable pageable);
    Department getDepartmentByCode(String code);
}
