package hello.hello_spring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hello_spring.domain.Member;
import hello.hello_spring.domain.repository.MemberRepository;
import hello.hello_spring.domain.repository.MemoryMemeberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // test 가 끝나면 rollback 하게 한다.
class MemberServiceIntegrationTest {

  @Autowired
  MemberService memberService;
  @Autowired
  MemberRepository memberRepository;

  @Test
  void 회원가입() {
    //give: ~상황에서
    Member member = new Member();
    member.setName("hello");

    //when : ~를 실행할 때
    Long saveId = memberService.join(member);

    //then : ~를 하자, 검증
    Member findMember = memberService.findOne(saveId).get();
    Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    //given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    //when
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class,
        () -> memberService.join(member2));
    Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//    try {
//      memberService.join(member2);
//      fail();
//    }catch (IllegalStateException e){
//      // 정상적을 성공할 경우 닿는 곳
//      Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//    }

    //then
  }
}