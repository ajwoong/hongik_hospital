package hongik.hospital.repository;

import hongik.hospital.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository <Department, Long>{
    Optional<Department> findByName(String name);
    List<Department> findByHospital_Id(Long id);
    List<Department> findByHospital_Name(String name);
}
