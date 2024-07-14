package hongik.hospital.dto;


import hongik.hospital.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberSigninRequestDTO {

    private  String name;
    private int age;
    private String gender;
    private String phone;
    private Address address;


}
