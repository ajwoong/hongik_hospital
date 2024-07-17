package hongik.hospital.service;

import hongik.hospital.dto.MemberSignInRequestDTO;
import hongik.hospital.dto.MemberSigninRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    @Rollback(value = false)
    void 중복회원예외_회원가입() {

        MemberSignInRequestDTO memberSignInRequestDTO = new MemberSignInRequestDTO();
        memberSignInRequestDTO.setName("kim");
        memberSignInRequestDTO.setPhone("010-5915-5203");
        memberService.join(memberSignInRequestDTO);

        MemberSignInRequestDTO memberSignInRequestDTO2 = new MemberSignInRequestDTO();
        memberSignInRequestDTO2.setName("kim");
        memberSignInRequestDTO2.setPhone("010-5915-523");
        memberService.join(memberSignInRequestDTO2);



    }
}