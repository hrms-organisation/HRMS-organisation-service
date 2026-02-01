package pfa.dev.organisatiojnservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.dev.organisatiojnservice.entities.Department;


public interface DepartmentRepository extends JpaRepository<Department,Long> {
    boolean existsDepartmentByCode(String code);
    boolean existsDepartmentByName(String name);

    Department getDepartmentByCode(String code);
}
