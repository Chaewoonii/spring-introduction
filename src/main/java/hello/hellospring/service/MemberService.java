package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// jpa를 사용할 땐 항상 transaction 이 필요. 데이터 저장 변경 시 항상 transaction 안에서 실행되어야 함
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * */
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // findByName 은 Optional 객체로 반환됨, ifPresent: 존재한다면 예외 발생
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     * */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
