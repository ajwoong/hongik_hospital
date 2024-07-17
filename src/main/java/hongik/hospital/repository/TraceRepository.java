package hongik.hospital.repository;

import hongik.hospital.domain.Trace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraceRepository extends JpaRepository <Trace, Long>{
    List<Trace> findByMember_NameAndMember_Phone(String name, String phone);
}
