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
    void 중복회원예외_회원가입() {

        MemberSigninRequestDTO memberSigninRequestDTO = new MemberSigninRequestDTO();
        memberSigninRequestDTO.setName("kim");
        memberSigninRequestDTO.setPhone("010-5915-5203");
        memberService.join(memberSigninRequestDTO);

        MemberSigninRequestDTO memberSigninRequestDTO2 = new MemberSigninRequestDTO();
        memberSigninRequestDTO2.setName("kim");
        memberSigninRequestDTO2.setPhone("010-5915-523");
        memberService.join(memberSigninRequestDTO2);



    }
}