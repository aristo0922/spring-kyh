package hello.hello_spring.service;

import static org.junit.jupiter.api.Assertions.*;

import hello.hello_spring.domain.Member;
import hello.hello_spring.domain.repository.MemoryMemeberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

  MemberService memberService ;
  MemoryMemeberRepository memberRepository ;

  @BeforeEach
  public void befreEach(){ // 동작하기 전에
    memberRepository=new MemoryMemeberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach // 메서드 별로 동작 끝난 뒤 하는 동작 지정
  public void afterEach(){
    memberRepository.clearStore(); // 돌 때마다 메모리 clear
  }

  /**
   * test 코드는 한글로 적어도 가능
   * 빌드될 떄 포함되지 않음
   */
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
  public void 중복_회원_예외(){
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

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}