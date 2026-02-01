package pfa.dev.organisatiojnservice.web;

import jakarta.ws.rs.GET;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.dev.organisatiojnservice.dto.DepartmentDto;
import pfa.dev.organisatiojnservice.entities.Department;
import pfa.dev.organisatiojnservice.service.DepartmentService;

@RestController
@RequestMapping("/dep")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping(value = "/create")
    public ResponseEntity<DepartmentDto> createDepartement(@RequestBody DepartmentDto departmentDto){
        return ResponseEntity.ok(departmentService.addDepartment(departmentDto));
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Department> getDepartementById(@PathVariable  Long id){
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }
    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<DepartmentDto> updateDepartement(@PathVariable Long id, @RequestBody DepartmentDto departmentDto){
        return ResponseEntity.ok(departmentService.updateDepartment(id,departmentDto));
    }
    @DeleteMapping(value = "/delete/{id}")
    public void deleteDepartement(@PathVariable Long id){
        departmentService.deleteDepartment(id);
    }
    @GetMapping(value = "/list")
    public ResponseEntity<Page<DepartmentDto>> getAllDepartments(@RequestParam(defaultValue = "0")  int page,@RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(departmentService.getAllDepartments(PageRequest.of(page,size)));
    }

    @GetMapping("/getByCode")
    public ResponseEntity<Department> getDepartmentByCode(@RequestParam String code){
        return ResponseEntity.ok(departmentService.getDepartmentByCode(code));
    }


}
