package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        /*
        * 테스트는 독립적으로 실행되어야 하기 때문에 매번 새로 생성해줌
        * memberService에 memberRepository를 넣어주는 것: 의존성 주입(Dependency Injection, DI)
        * */
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    // 테스트코드는 한글 이름으로 쓰기도 함.
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("member1");

        // when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 회원가입 시 중복 회원 검증
    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // memberService.join(member2)를 실행할 때 IllegalStateException 예외가 터저야 함,

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        memberService.join(member1);
        try{
            memberService.join(member2);
            fail(); // 이름이 같다면 예외가 발생해야하는데 예외가 발생하지 않음. 실패
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/


        // then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}