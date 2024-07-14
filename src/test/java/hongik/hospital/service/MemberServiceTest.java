package hongik.hospital.service;

import hongik.hospital.domain.Member;
import hongik.hospital.dto.MemberSigninRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    @Rollback(value = false)
    void join() {

        MemberSigninRequestDTO memberSigninRequestDTO = new MemberSigninRequestDTO();
        memberSigninRequestDTO.setName("kim");
        memberSigninRequestDTO.setPhone("010-5915-5203");
        memberService.join(memberSigninRequestDTO);

        Member kim = memberService.findMemberByNamePhone("kim", "010-5915-5203");


    }
}