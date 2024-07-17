package hongik.hospital.service;

import hongik.hospital.domain.Member;
import hongik.hospital.dto.MemberFoundResponseDTO;
import hongik.hospital.dto.MemberSignInRequestDTO;
import hongik.hospital.repository.MemberRepository;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    // 실제 서비스 //

    private final MemberRepository memberRepository;

    // 새로운 멤버 저장
    @Transactional
    public void join(MemberSignInRequestDTO memberSignInRequestDTO){
        Member member = makeMember(memberSignInRequestDTO);
        validateDuplicateMember(member);
        memberRepository.save(member);
    }


    // 이름과 번호로 특정 멤버 조회후 멤버 dto로 리턴
    public MemberFoundResponseDTO findMemberByNamePhone(String memberName, String memberPhone){
        Member member = memberRepository.findByNameAndPhone(memberName, memberPhone)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        return getMemberFoundResponseDTO(member);
    }

    // 모든 멤버를 조회후 멤버 dto list로 리턴
    public List<MemberFoundResponseDTO> findMembers(){
        List<Member> members = memberRepository.findAll();
        return getMemberFoundResponseDTOS(members);
    }




    // == 구현한 함수 == //

    private Member makeMember(MemberSignInRequestDTO memberSignInRequestDTO) {
        Member member = new Member();
        member.setName(memberSignInRequestDTO.getName());
        member.setAge(memberSignInRequestDTO.getAge());
        member.setGender(memberSignInRequestDTO.getGender());
        member.setPhone(memberSignInRequestDTO.getPhone());
        member.setAddress(memberSignInRequestDTO.getAddress());
        return member;
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByNameAndPhone(member.getName(), member.getPhone());
        if(findMember.isPresent()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    private static MemberFoundResponseDTO getMemberFoundResponseDTO(Member member) {
        MemberFoundResponseDTO memberFoundResponseDTO = new MemberFoundResponseDTO();
        memberFoundResponseDTO.setName(member.getName());
        memberFoundResponseDTO.setAge(member.getAge());
        memberFoundResponseDTO.setPhone(member.getPhone());
        memberFoundResponseDTO.setGender(member.getGender());
        memberFoundResponseDTO.setAddress(member.getAddress());
        return memberFoundResponseDTO;
    }

    private static List<MemberFoundResponseDTO> getMemberFoundResponseDTOS(List<Member> members) {
        List<MemberFoundResponseDTO> membersDTO = new ArrayList<>();

        for (Member member : members) {
            MemberFoundResponseDTO found = new MemberFoundResponseDTO();
            found.setAddress(member.getAddress());
            found.setName(member.getName());
            found.setPhone(member.getPhone());
            found.setAge(member.getAge());
            found.setGender(member.getGender());
            membersDTO.add(found);
        }
        return membersDTO;
    }



}
