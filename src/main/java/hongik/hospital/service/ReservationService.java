package hongik.hospital.service;

import hongik.hospital.domain.Member;
import hongik.hospital.domain.Reservation;
import hongik.hospital.domain.ReserveStatus;
import hongik.hospital.dto.MemberFoundResponseDTO;
import hongik.hospital.dto.MemberReservationResponseDTO;
import hongik.hospital.dto.MemberSearchRequestDTO;
import hongik.hospital.dto.NewReservationDTO;
import hongik.hospital.repository.DoctorRepository;
import hongik.hospital.repository.MemberRepository;
import hongik.hospital.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final DoctorRepository doctorRepository;

    // 실제 서비스 //

    // 예약 생성
    @Transactional
    public void reserve(NewReservationDTO newReservationDTO){
        Reservation reservation = makeReserve(newReservationDTO);
        reservation.setReserveStatus(ReserveStatus.PROG);
        reservationRepository.save(reservation);
    }

    // 예약 취소
    @Transactional
    public void cancel(Long reservationId){
        reservationRepository.deleteById(reservationId);
    }

    // 특정 회원(이름, 전화번호)의 예약 리스트 조회
    public List<MemberReservationResponseDTO> findMemberReserve(MemberSearchRequestDTO memberSearchRequestDTO){
        Member member = memberRepository.findByNameAndPhone(memberSearchRequestDTO.getName(), memberSearchRequestDTO.getPhone())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        List<Reservation> memberReserves = reservationRepository.findByMember_Id(member.getId());

        return getMemberReservationResponseDTOS(memberReserves);
    }


    // 특정 예약 상태 예약중에서 완료로 변경
    @Transactional
    public void makeReserveComp(Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 예약내역 입니다."));
        reservation.setReserveStatus(ReserveStatus.COMP);
    }

    // 서비스 구현 함수 //

    private Reservation makeReserve(NewReservationDTO newReservationDTO) {

        Reservation reservation = new Reservation();
        reservation.setMember(memberRepository.findById(newReservationDTO.getMemberId())
                .orElseThrow(()-> new IllegalStateException("존재하지 않는 회원입니다.")));
        reservation.setDoctor(doctorRepository.findById(newReservationDTO.getDoctorId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 의사입니다.")));
        reservation.setReservationTime(newReservationDTO.getReservationTime());
        return reservation;
    }

    private static List<MemberReservationResponseDTO> getMemberReservationResponseDTOS(List<Reservation> memberReserves) {
        List<MemberReservationResponseDTO> memberReservationResponseDTOS = new ArrayList<>();

        for (Reservation memberReserve : memberReserves) {
            MemberReservationResponseDTO reservationDTO = new MemberReservationResponseDTO();
            reservationDTO.setMember_name(memberReserve.getMember().getName());
            reservationDTO.setDoctor_name(memberReserve.getDoctor().getName());
            reservationDTO.setReservationTime(memberReserve.getReservationTime());
            memberReservationResponseDTOS.add(reservationDTO);
        }

        return memberReservationResponseDTOS;
    }




}
