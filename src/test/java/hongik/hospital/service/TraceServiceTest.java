package hongik.hospital.service;

import hongik.hospital.domain.Doctor;
import hongik.hospital.domain.Member;
import hongik.hospital.domain.Reservation;
import hongik.hospital.domain.ReserveStatus;
import hongik.hospital.repository.MemberRepository;
import hongik.hospital.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TraceServiceTest {

    @Autowired
    TraceService traceService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired
    ReservationRepository reservationRepository;

    @Test
    @Rollback(value = false)
    void trace() {

        Member member = new Member();
        member.setName("kim");
        member.setPhone("010-5915-5203");
        memberRepository.save(member);

        Doctor doctor = new Doctor();
        doctor.setName("김내과");
        doctorService.join(doctor);

        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setDoctor(doctor);
        reservationRepository.save(reservation);
        reservation.setReserveStatus(ReserveStatus.COMP);

        traceService.trace(reservation.getId(), 20000);


    }
}