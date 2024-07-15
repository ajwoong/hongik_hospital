package hongik.hospital.service;

import hongik.hospital.domain.Hospital;
import hongik.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Transactional
    public void join(Hospital hospital){
        hospitalRepository.save(hospital);
    }

    @Transactional
    public void delete(Long hospitalId){
        hospitalRepository.deleteById(hospitalId);
    }

    public Optional<Hospital> findOne(Long hospitalId){
        return hospitalRepository.findById(hospitalId);
    }

    public List<Hospital> findHospitals(){
         return hospitalRepository.findAll();
    }

    public List<Hospital> findHospitalsByName(String hospitalName){
        return hospitalRepository.findAllByName(hospitalName);
    }

}
