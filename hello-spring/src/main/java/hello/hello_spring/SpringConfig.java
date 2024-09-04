package hello.hello_spring;

import hello.hello_spring.domain.repository.JdbcMemberRepository;
import hello.hello_spring.domain.repository.JdbcTemplateMemberRepository;
import hello.hello_spring.domain.repository.JpaMemberRepository;
import hello.hello_spring.domain.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  private final MemberRepository memberRepository;

  @Autowired // 생략 가능(생성자가 1개)
  public SpringConfig(MemberRepository memberRepository){
    this.memberRepository = memberRepository;
  }


  @Bean// 빈으로 등록
  public MemberService memberService(){
    return new MemberService(memberRepository); // command p : 매개변수 타입 파악
  }

//  @Bean
//  public MemberRepository memberRepository(){
//    return new JdbcMemberRepository(dataSource);
//    return new MemoryMemeberRepository();
//    return new JdbcTemplateMemberRepository(dataSource);
//    return new JpaMemberRepository(em);
//  }
}
