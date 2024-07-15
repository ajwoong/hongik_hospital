package hongik.hospital.service;

import hongik.hospital.domain.Doctor;
import hongik.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Transactional
    public void join(Doctor doctor){
        doctorRepository.save(doctor);
    }

    @Transactional
    public void delete(Doctor doctor){
        doctorRepository.deleteById(doctor.getId());
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


}
