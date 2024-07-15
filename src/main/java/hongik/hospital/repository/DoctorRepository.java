package hongik.hospital.repository;

import hongik.hospital.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository <Doctor, Long> {

    List<Doctor> findByName(String name);
    List<Doctor> findByDepartment_Id(Long id);
}
