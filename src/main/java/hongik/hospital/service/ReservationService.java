package hongik.hospital.service;

import hongik.hospital.domain.Member;
import hongik.hospital.domain.Reservation;
import hongik.hospital.domain.ReserveStatus;
import hongik.hospital.dto.MemberSearchRequestDTO;
import hongik.hospital.dto.NewReservationDTO;
import hongik.hospital.repository.DoctorRepository;
import hongik.hospital.repository.MemberRepository;
import hongik.hospital.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final DoctorRepository doctorRepository;

    // 실제 서비스 //

    @Transactional
    public void reserve(NewReservationDTO newReservationDTO){
        Reservation reservation = makeReserve(newReservationDTO);
        reservation.setReserveStatus(ReserveStatus.PROG);
        reservationRepository.save(reservation);
    }

    @Transactional
    public void cancel(Long reservationId){
        reservationRepository.deleteById(reservationId);
    }

    public List<Reservation> findMemberReserve(MemberSearchRequestDTO memberSearchRequestDTO){
        Member member = memberRepository.findByNameAndPhone(memberSearchRequestDTO.getName(), memberSearchRequestDTO.getPhone());
        return reservationRepository.findByMember_Id(member.getId());
    }

    @Transactional
    public void makeReserveComp(Reservation reservation){
        reservation.setReserveStatus(ReserveStatus.COMP);
    }


    // 서비스 구현 함수 //

    private Reservation makeReserve(NewReservationDTO newReservationDTO) {

        Reservation reservation = new Reservation();
        reservation.setMember(memberRepository.findById(newReservationDTO.getMemberId()).get());
        reservation.setDoctor(doctorRepository.findById(newReservationDTO.getDoctorId()).get());
        reservation.setReservationTime(newReservationDTO.getReservationTime());
        return reservation;
    }




}
