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
}
