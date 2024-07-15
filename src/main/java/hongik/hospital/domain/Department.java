package hongik.hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Department {

    @Id @GeneratedValue
    @Column(name = "department_id")
    private Long id;

    private String name;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors = new ArrayList<>();


}
