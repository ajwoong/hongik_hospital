package hongik.hospital.repository;

import hongik.hospital.domain.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DoctorRepositoryTest {

    @Autowired DoctorRepository doctorRepository;

    @Test
    @Rollback(value = false)
    public void 의사확인(){
        Doctor doctor = new Doctor();
        doctor.setName("doctor1");
        doctor.setCareer(4);
        doctorRepository.save(doctor);

        List<Doctor> doctorList = doctorRepository.findByName("doctor1");

    }
    
    


}