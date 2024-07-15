package hongik.hospital.service;

import hongik.hospital.domain.Department;
import hongik.hospital.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // 실제 서비스 //

    public void join(Department department){
        validateDuplicateDepartment(department);
        departmentRepository.save(department);
    }

    public List<Department> findDepartments(){
        return departmentRepository.findAll();
    }




    // 서비스 구현 함수 //

    private void validateDuplicateDepartment(Department department) {
        Optional<Department> duplicate = departmentRepository.findByName(department.getName());
        if(duplicate.isPresent()){
           throw new IllegalStateException("이미 존재하는 진료과입니다.");
        }
    }

}
