package hongik.hospital.dto;

import lombok.Getter;
import lombok.Setter;

// 멤버별 병원 기록 제공하는 dto
@Getter @Setter
public class MemberTraceResponseDTO {

    private String doctor_name;
    private String department_name;
    private String hospital_name;
    private int price;

}
