package hongik.hospital.domain;

import hongik.hospital.dto.MemberSigninRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String phone;
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private  List<Record> records = new ArrayList<>();


    public void change(MemberSigninRequestDTO memberSigninRequestDTO){
        this.name = memberSigninRequestDTO.getName();
        this.age = memberSigninRequestDTO.getAge();
        this.gender = memberSigninRequestDTO.getGender();
        this.phone = memberSigninRequestDTO.getPhone();
        this.address= memberSigninRequestDTO.getAddress();
    }


}