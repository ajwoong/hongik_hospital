package hongik.hospital.service;

import hongik.hospital.domain.Member;
import hongik.hospital.domain.Reservation;
import hongik.hospital.domain.ReserveStatus;
import hongik.hospital.dto.MemberSearchRequestDTO;
import hongik.hospital.dto.NewReservationDTO;
import hongik.hospital.repository.MemberRepository;
import hongik.hospital.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    public void reserve(NewReservationDTO newReservationDTO){
        Reservation reservation = new Reservation();
        reservation.change(newReservationDTO);
        reservation.setReserveStatus(ReserveStatus.PROG);
        reservationRepository.save(reservation);
    }

    public void cancel(Long reservationId){
        Reservation reservation = reservationRepository.findOne(reservationId);
        reservation.setReserveStatus(ReserveStatus.CANCEL);
    }

    public List<Reservation> findMemberReserve(MemberSearchRequestDTO memberSearchRequestDTO){
        Member member = memberRepository.findByNamePhone(memberSearchRequestDTO.getName(), memberSearchRequestDTO.getPhone());
        return reservationRepository.findByMember(member.getId());
    }





}
