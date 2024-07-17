package hongik.hospital.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter @Setter
public class MemberReservationResponseDTO {

    private String member_name;
    private String doctor_name;
    private LocalDateTime reservationTime;

}
