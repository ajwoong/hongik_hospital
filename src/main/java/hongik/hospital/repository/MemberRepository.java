package hongik.hospital.repository;

import hongik.hospital.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNameAndPhone(String name, String phone);

}


//@Repository
//@RequiredArgsConstructor
//public class MemberRepository {
//
//    private final EntityManager em;
//
//    public void save(Member member){
//        em.persist(member);
//    }
//
//    public Member findOne(Long id){
//        return em.find(Member.class, id);
//    }
//
//    public List<Member> findAll(){
//        return em.createQuery("select m from Member m", Member.class).getResultList();
//    }
//
//    public Member findByNamePhone(String name, String phone){
//        return em.createQuery("select m from Member m where m.name = :name and m.phone = :phone", Member.class)
//                .setParameter("name", name)
//                .setParameter("phone", phone)
//                .getSingleResult();
//    }
//
//}
