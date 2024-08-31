package hello.hello_spring.repository;

//import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*; // static

import hello.hello_spring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemeberRepositoryTest {

  MemoryMemeberRepository repository = new MemoryMemeberRepository();

  @AfterEach // 메서드 별로 동작 끝난 뒤 하는 동작 지정
  public void afterEach(){
    repository.clearStore();
  }
  @Test
  public void save() {
    Member member = new Member();
    member.setName("spring");

    repository.save(member);
    Member result = repository.findById(member.getId()).get();
//    Assertions.assertEquals(member, result); // junit ...
    assertThat(member).isEqualTo(result); // member 가 result 와 같다
  }

  @Test
  void findById() {
  }

  @Test
  void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

//    Member member2 = new Member();
    Member result = repository.findByName("spring1").get();
    assertThat(result).isEqualTo(member1);
  }

  @Test
  void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    List<Member> result = repository.findAll();
    assertThat(result.size()).isEqualTo(2);
  }

}