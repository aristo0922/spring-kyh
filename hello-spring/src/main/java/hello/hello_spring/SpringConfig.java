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
//  private final DataSource dataSource;
//  public SpringConfig(DataSource dataSource){
//    this.dataSource = dataSource;
//  }

  private EntityManager em;

  @Autowired
  public SpringConfig(EntityManager em){
    this.em = em;
  }

  @Bean// 빈으로 등록
  public MemberService memberService(){
    return new MemberService(memberRepository()); // command p : 매개변수 타입 파악
  }

  @Bean
  public MemberRepository memberRepository(){
//    return new JdbcMemberRepository(dataSource);
//    return new MemoryMemeberRepository();
//    return new JdbcTemplateMemberRepository(dataSource);
    return new JpaMemberRepository(em);
  }
}
