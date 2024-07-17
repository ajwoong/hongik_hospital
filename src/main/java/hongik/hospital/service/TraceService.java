package hongik.hospital.service;

import hongik.hospital.domain.Trace;
import hongik.hospital.domain.Reservation;
import hongik.hospital.domain.ReserveStatus;
import hongik.hospital.dto.MemberTraceResponseDTO;
import hongik.hospital.repository.ReservationRepository;
import hongik.hospital.repository.TraceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TraceService {

    private final TraceRepository traceRepository;
    private final ReservationRepository reservationRepository;

    // 병원 기록을 생성 (기존 예약중이던 기록을 예약 완료로 만들고 기록테이블에도 추가)
    public void trace(Long reservationId, int price){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new IllegalStateException("존재하지 않는 예약 내역입니다"));

        if(reservation.getReserveStatus() == ReserveStatus.COMP){
            Trace trace = new Trace();
            trace.setMember(reservation.getMember());
            trace.setDoctor(reservation.getDoctor());
            trace.setPrice(price);
            traceRepository.save(trace);
        }

        else throw new IllegalStateException("아직 완료되지 않은 진료입니다.");
    }

    // 특정 멤버(이름,번호)의 병원기록 제공(병원이름, 진료과이름, 의사이름, 가격)
    public List<MemberTraceResponseDTO> findMemberTraces(String name, String phone){
        List<Trace> memberTraces = traceRepository.findByMember_NameAndMember_Phone(name, phone);
        return getMemberTraceResponseDTOS(memberTraces);
    }



    // == 구현을 위한 함수 == //

    private static List<MemberTraceResponseDTO> getMemberTraceResponseDTOS(List<Trace> memberTraces) {
        List<MemberTraceResponseDTO> memberTracesDTO = new ArrayList<>();
        for (Trace memberTrace : memberTraces) {
             MemberTraceResponseDTO traceDTO = new MemberTraceResponseDTO();
             traceDTO.setDoctor_name(memberTrace.getDoctor().getName());
             traceDTO.setDepartment_name(memberTrace.getDoctor().getDepartment().getName());
             traceDTO.setHospital_name(memberTrace.getDoctor().getDepartment().getHospital().getName());
             traceDTO.setPrice(memberTrace.getPrice());
             memberTracesDTO.add(traceDTO);
        }
        return memberTracesDTO;
    }


}
