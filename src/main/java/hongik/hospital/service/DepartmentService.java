package hongik.hospital.service;

import hongik.hospital.domain.Department;
import hongik.hospital.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // 실제 서비스 //

    @Transactional
    public void join(Department department){
        validateDuplicateDepartment(department);
        departmentRepository.save(department);
    }

    @Transactional
    public void delete(Long departmentId){
        departmentRepository.deleteById(departmentId);
    }

    public Optional<Department> findOne(Long departmentId){
        return departmentRepository.findById(departmentId);
    }

    public List<Department> findDepartments(){
        return departmentRepository.findAll();
    }

    public List<Department> findDepartmentsByHospital(Long hospitalId){
        return departmentRepository.findByHospital_Id(hospitalId);
    }

    public List<Department> findDepartmentsByHospitalName(String hospitalName){
        return departmentRepository.findByHospital_Name(hospitalName);
    }



    // 서비스 구현 함수 //

    private void validateDuplicateDepartment(Department department) {
        Optional<Department> duplicate = departmentRepository.findByName(department.getName());
        if(duplicate.isPresent()){
           throw new IllegalStateException("이미 존재하는 진료과입니다.");
        }
    }

}
