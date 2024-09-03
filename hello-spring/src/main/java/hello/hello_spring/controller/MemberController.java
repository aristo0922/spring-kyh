package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
  private final MemberService memberService ;

  /**
   * // 컨테이너를 띄울 때 생성자 호출, Autowired: spring 이 가진 memberService 를 찾아서 연결시켜준다.
   * @param memberService
   */
  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/members/new")
  public String creatForm(){
    return "members/createMemberForm";
  }

  @PostMapping("/members/new")
  public String create(MemberForm form){
    Member member = new Member();
    member.setName(form.getName());

    memberService.join(member);

    return "redirect:/" ; // 홈 화면으로
  }

  @GetMapping("/members")
  public String list(Model model){
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
  }
}
