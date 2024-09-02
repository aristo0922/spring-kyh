package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
