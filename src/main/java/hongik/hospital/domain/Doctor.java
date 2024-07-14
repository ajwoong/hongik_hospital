package hongik.hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Doctor {

    @Id @GeneratedValue
    @Column(name = "doctor_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    private String name;
    private int age;
    private String gender;
    private String phone;
    private int career;

    @OneToMany(mappedBy = "doctor")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "doctor")
    private  List<Record> records = new ArrayList<>();

}
