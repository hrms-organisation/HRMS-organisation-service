package pfa.dev.organisatiojnservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pfa.dev.organisatiojnservice.dto.DepartmentSummaryResponse;
import pfa.dev.organisatiojnservice.dto.DepartmentDto;
import pfa.dev.organisatiojnservice.entities.Department;
import pfa.dev.organisatiojnservice.entities.Job;
import pfa.dev.organisatiojnservice.mapper.DepartmentMapper;
import pfa.dev.organisatiojnservice.mapper.JobMapper;
import pfa.dev.organisatiojnservice.repositories.DepartmentRepository;
import pfa.dev.organisatiojnservice.repositories.JobRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;


    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        if (departmentRepository.existsDepartmentByCode(departmentDto.getCode())) {
            throw new IllegalArgumentException("Department with code " + departmentDto.getCode() + " already exists");
        }
        if (departmentRepository.existsDepartmentByName(departmentDto.getName())) {
            throw new IllegalArgumentException("Department with name " + departmentDto.getName() + " already exists");
        }

        Department department = departmentMapper.toEntity(departmentDto);
        department = departmentRepository.save(department);

        return departmentMapper.toDto(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return existDep(id);
    }

    @Override
    public DepartmentSummaryResponse getDepartmentSummaryById(Long id) {
        Department department = existDep(id);
        return new DepartmentSummaryResponse(department.getId(), department.getCode());
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
     existDep(id);

     Department dep =departmentRepository.save(departmentMapper.toEntity(departmentDto));
     return departmentMapper.toDto(dep);
    }

    @Override
    public void deleteDepartment(Long id) {
        existDep(id);
        departmentRepository.deleteById(id);

    }

    @Override
    public Page<DepartmentDto> getAllDepartments(Pageable pageable) {
        Page<Department> departments = departmentRepository.findAll(pageable);
        return departments.map(departmentMapper::toDto);
    }

    @Override
    public Department getDepartmentByCode(String code) {
        Department department = departmentRepository.getDepartmentByCode(code);
        if (department == null) {
            throw new IllegalArgumentException("Department with code " + code + " does not exist");
        }
        return department ;
    }

    private Department existDep(Long id){
        return departmentRepository.findById(id).orElseThrow(()->new RuntimeException("Department not found with id: "+id));

    }
}
