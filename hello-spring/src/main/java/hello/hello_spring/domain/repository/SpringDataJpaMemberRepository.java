package hello.hello_spring.domain.repository;

import hello.hello_spring.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

//<객체, id>
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> ,MemberRepository{ // interface 가 인터페이스 받을때는 extends

  @Override
  Optional<Member> findByName(String name);
}
