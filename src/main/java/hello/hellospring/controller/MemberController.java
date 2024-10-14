package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {


    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;

        // AOP: 실제로 MemberService가 아니라 프록시가 호출됨.
        // memberService = class hello.hellospring.service.MemberService$$SpringCGLIB$$0
        // SpringCGLIB: 코드를 복제하여(MemberService) 조작하는 기술
        // 프록시를 통해 AOP가 실행되고, joinPoint.proced()를 만나면 실제 MemberService의 로직이 호출됨.
        System.out.println("memberService = " + memberService.getClass()); // 프록시 호출 확인
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
