package hongik.hospital.dto;

import hongik.hospital.domain.Doctor;
import hongik.hospital.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter @Setter
public class NewReservationDTO {

    private Long memberId;
    private Long doctorId;
    private LocalDateTime reservationTime;

}
