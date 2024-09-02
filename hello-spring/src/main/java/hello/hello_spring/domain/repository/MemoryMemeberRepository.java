package hello.hello_spring.domain.repository;

import hello.hello_spring.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemeberRepository implements MemberRepository{

  // 동시성 문제를 고려하지 않고 단순하게 진행
  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L; // key 값 생성하는 ...

  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id)); // null 을 반환하더라도 처리할 수 있도록;
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
        .filter(member -> member.getName().equals(name))
        .findAny(); // 하나 찾아서 반환
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }


  public void clearStore(){
    store.clear();
  }
}
