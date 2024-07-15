package hongik.hospital.controller;

import hongik.hospital.dto.MemberSigninRequestDTO;
import hongik.hospital.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/member")
    public String createMember(MemberSigninRequestDTO memberSigninRequestDTO){
        memberService.join(memberSigninRequestDTO);
        return "redirect:/";
    }


}
