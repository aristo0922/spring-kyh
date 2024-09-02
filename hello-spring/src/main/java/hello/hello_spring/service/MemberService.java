package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.domain.repository.MemberRepository;
import hello.hello_spring.domain.repository.MemoryMemeberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository){ // dependency injection, 외부에서 repo 객체 주입
    this.memberRepository = memberRepository;
  }

//  회원가입
//  public Long join(Member member){
////    같은 이름을 가진 중복 회원을 만들면 안된다. command option v -> return 까지 자동완성
//    Optional<Member> result  = memberRepository.findByName(member.getName());
//    // 혹은 orElse: 아니면 -를 해라
//    result.ifPresent(m-> {
//      throw new IllegalStateException("이미 존재하는 회원입니다.");
//    });

  /**
   * 회원가입
   * @param member
   * @return memberId
   */
    public Long join(Member member){
      validateExistMember(member);

      memberRepository.save(member);
      return member.getId();
    }

  /**
   * 중복회원 검증
   * @param member
   * controll t: 메서드 추출하여 함수화
   */
  private void validateExistMember(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }// ctrl + t

  /**
   * 전체 회원 조회
   * 서비스: 비즈니스에 가까운 용어로 네이밍
   * 리포지토리: 기계적, 개발같은 용어로 네이밍
   * @return List<Member></>
   */
  public List<Member> findMembers(){
      return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId){
      return memberRepository.findById(memberId);
  }

//  }

}
