package hongik.hospital.service;

import hongik.hospital.domain.Member;
import hongik.hospital.dto.MemberSigninRequestDTO;
import hongik.hospital.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    // 실제 서비스 //

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberSigninRequestDTO memberSigninRequestDTO){
        Member member = makeMember(memberSigninRequestDTO);
        validateDuplicateMember(member);
        memberRepository.save(member);
    }


    public Member findMemberByNamePhone(String memberName, String memberPhone){
        return memberRepository.findByNameAndPhone(memberName, memberPhone);
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }



    // == 구현한 함수 == //

    private Member makeMember(MemberSigninRequestDTO memberSigninRequestDTO) {
        Member member = new Member();
        member.setName(memberSigninRequestDTO.getName());
        member.setAge(memberSigninRequestDTO.getAge());
        member.setGender(memberSigninRequestDTO.getGender());
        member.setPhone(memberSigninRequestDTO.getPhone());
        member.setAddress(memberSigninRequestDTO.getAddress());
        return member;
    }

    private void validateDuplicateMember(Member member) {
        List<Member> all = memberRepository.findAll();
        if(!all.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }



}
