package hongik.hospital.repository;

import hongik.hospital.domain.Reservation;
import hongik.hospital.domain.ReserveStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByMember_Id(Long id);
    
}


//@Repository
//@RequiredArgsConstructor
//public class ReservationRepository {
//
//    private final EntityManager em;
//
//    public void save(Reservation reservation){
//        em.persist(reservation);
//    }
//
//    public Reservation findOne(Long reservationId){
//        return em.find(Reservation.class, reservationId);
//    }
//
//    public List<Reservation> findByMember(Long memberId){
//        return em.createQuery("select r from Reservation r where r.member.id =:memberId", Reservation.class)
//                .setParameter("memberId" , memberId)
//                .getResultList();
//    }
//
//    public List<Reservation> findAll(){
//        return em.createQuery("select r from Reservation r", Reservation.class)
//                .getResultList();
//    }
//
//    public List<Reservation> findAllByStatus(ReserveStatus reserveStatus){
//        return em.createQuery("select r from Reservation r where r.reserveStatus = :reserveStatus ", Reservation.class)
//                .setParameter("reserveStatus", reserveStatus)
//                .getResultList();
//    }
//
//}
