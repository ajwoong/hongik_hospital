package hongik.hospital.service;

import hongik.hospital.domain.Department;
import hongik.hospital.domain.Doctor;
import hongik.hospital.domain.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class DoctorServiceTest {

    @Autowired private HospitalService hospitalService;
    @Autowired private DepartmentService departmentService;
    @Autowired private DoctorService doctorService;


    @Test
    @Rollback(value = false)
    void findDoctorsByDepartmentName() {
        Hospital hospital1 = new Hospital();
        hospital1.setName("helloHospital");
        hospitalService.join(hospital1);

        Hospital hospital2 = new Hospital();
        hospital2.setName("strangeHospital");
        hospitalService.join(hospital2);

        Department department1 = new Department();
        department1.setHospital(hospital1);
        department1.setName("신경과");
        departmentService.join(department1);

        Department department2 = new Department();
        department2.setHospital(hospital1);
        department2.setName("내과");
        departmentService.join(department2);

        List<Department> departments = departmentService.findDepartmentsByHospital(hospital2.getId());
        for (Department department : departments) {
            String name = department.getName();
            System.out.println("name = " + name);
        }

        Doctor doctor = new Doctor();
        doctor.setName("김내과");
        doctor.setDepartment(department2);
        doctorService.join(doctor);

        List<Doctor> list = doctorService.findDoctorsByDepartmentName("내과");
        for (Doctor doctor1 : list) {
            String name = doctor1.getName();
            System.out.println("name = " + name);
        }

    }


}