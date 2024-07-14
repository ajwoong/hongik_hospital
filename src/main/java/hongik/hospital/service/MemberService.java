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

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberSigninRequestDTO memberSigninRequestDTO){
        Member member = new Member();
        member.change(memberSigninRequestDTO);
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> all = memberRepository.findAll();
        if(!all.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Member findMemberByNamePhone(String memberName, String memberPhone){
        return memberRepository.findByNamePhone(memberName, memberPhone);
    }




}
