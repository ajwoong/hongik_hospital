package hongik.hospital.service;

import hongik.hospital.domain.Doctor;
import hongik.hospital.domain.Member;
import hongik.hospital.domain.Reservation;
import hongik.hospital.dto.MemberSearchRequestDTO;
import hongik.hospital.dto.MemberSigninRequestDTO;
import hongik.hospital.dto.NewReservationDTO;
import hongik.hospital.repository.MemberRepository;
import hongik.hospital.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReservationServiceTest {

    @Autowired ReservationService reservationService;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    DoctorService doctorService;

    @Test
    @Rollback(value = false)
    void findMemberReserve() {
        Member member = new Member();
        member.setName("kim");
        member.setPhone("010-5915-5203");
        memberRepository.save(member);

        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservationRepository.save(reservation);


        MemberSearchRequestDTO memberSearchRequestDTO = new MemberSearchRequestDTO();
        memberSearchRequestDTO.setName("kim");
        memberSearchRequestDTO.setPhone("010-5915-5203");

        List<Reservation> memberReserve = reservationService.findMemberReserve(memberSearchRequestDTO);


    }

    @Test
    @Rollback(value = false)
    void changeReservationStatus(){

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

        System.out.println("reservation.getReserveStatus() = " + reservation.getReserveStatus());

        reservationService.makeReserveComp(reservation);

        System.out.println("reservation.getReserveStatus() = " + reservation.getReserveStatus());

    }
}