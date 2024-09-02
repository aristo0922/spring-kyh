package hello.hello_spring;

import hello.hello_spring.domain.repository.MemberRepository;
import hello.hello_spring.domain.repository.MemoryMemeberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Springconfig {
  @Bean// 빈으로 등록
  public MemberService memberService(){
    return new MemberService(memberRepository()); // command p : 매개변수 타입 파악
  }

  @Bean
  public MemberRepository memberRepository(){
    return new MemoryMemeberRepository();
  }
  /**
   * 서비스와 레포 빈에 등록
   * 등록된 레포를 서비스에 삽입
   * 서비스-레포지토리 연결
   *
   * 컨트롤러는 스프링이 관리하기 때문에 @Controller 를 사용해야 한다.
   * -> Autowired 로 서비스와 연결
   */

}
