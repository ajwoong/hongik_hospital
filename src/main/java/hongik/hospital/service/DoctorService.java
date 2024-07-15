package hongik.hospital.service;

import hongik.hospital.domain.Doctor;
import hongik.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Transactional
    public void join(Doctor doctor){
        doctorRepository.save(doctor);
    }

    @Transactional
    public void delete(Long doctorId){
        doctorRepository.deleteById(doctorId);
    }

    public Optional<Doctor> findOne(Long doctorId){
        return doctorRepository.findById(doctorId);
    }

    public List<Doctor> findDoctors(){
        return doctorRepository.findAll();
    }

    public List<Doctor> findDoctorsByName(String name){
        return doctorRepository.findByName(name);
    }

    public List<Doctor> findDoctorsByDepartment(Long departmentId){
       return doctorRepository.findByDepartment_Id(departmentId);
    }

    public List<Doctor> findDoctorsByDepartmentName(String departmentName){
        return doctorRepository.findByDepartment_Name(departmentName);
    }


}
