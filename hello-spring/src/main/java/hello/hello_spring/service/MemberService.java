package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemeberRepository;
import java.util.List;
import java.util.Optional;

public class MemberService {
  private final MemberRepository memberRepository = new MemoryMemeberRepository();

//  회원가입
//  public Long join(Member member){
////    같은 이름을 가진 중복 회원을 만들면 안된다.
//    Optional<Member> result  = memberRepository.findByName(member.getName());
//    // 혹은 orElse: 아니면 -를 해라
//    result.ifPresent(m-> {
//      throw new IllegalStateException("이미 존재하는 회원입니다.");
//    });

    public Long join(Member member){
      validateExistMember(member);

      memberRepository.save(member);
      return member.getId();
    }

  private void validateExistMember(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }// ctrl + t

  public List<Member> findMembers(){
      return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId){
      return memberRepository.findById(memberId);
  }

//  }

}
