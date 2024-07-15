package hongik.hospital.service;

import hongik.hospital.domain.Trace;
import hongik.hospital.domain.Reservation;
import hongik.hospital.domain.ReserveStatus;
import hongik.hospital.repository.ReservationRepository;
import hongik.hospital.repository.TraceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TraceService {

    private final TraceRepository traceRepository;
    private final ReservationRepository reservationRepository;

    public void trace(Long reservationId, int price){
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);

        if(reservation.get().getReserveStatus() == ReserveStatus.COMP){
            Trace trace = new Trace();
            trace.setMember(reservation.get().getMember());
            trace.setDoctor(reservation.get().getDoctor());
            trace.setPrice(price);
            traceRepository.save(trace);
        }

        else throw new IllegalStateException("아직 완료되지 않은 진료입니다.");
    }



}
