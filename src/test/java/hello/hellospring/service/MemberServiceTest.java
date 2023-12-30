package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
//    위 코드 처럼 작성하면 MemberService에서 생성된 MemberRepository와
//    Test클래스에서 생성된 MemberRepository와 다른 객체이므로 적적하지 않다.
//    해결방법 -> Service내의 Repository의 객체를 외부에서 주입시켜주도록 변경한다.
//    이것이 DI (Dependency Injection) "의존성 주입" 이다.

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        // 각 테스트 함수가 실행되기 전에 실행되는 함수이다.
        // repo 객체를 생성하여 service에 주입시킨다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // 테스트 함수명은 한글로 작성해도 무방하며, 가독성면에서 더 좋을 수도 있다.
        // given, when, then은 특별한 문법이 아니라 그냥 가독성 측면에서 도와주는 주석과 같은 것이다.

        // given -> 다음과 같이 주어졌을 때
        Member member1 = new Member();
        member1.setName("spring");

        // when -> 다음과 같이 실행하면
        Long memberId = memberService.join(member1);

        // then -> 결과는 다음과 같이 나와야 해
        Member result = memberService.findOne(memberId).get();
        assertThat(result.getName()).isEqualTo(member1.getName());
    }

    // 테스트 코드는 로직이 정상적으로 굴러갈때도 테스트 해야하지만
    // 예외처리를 잘 하는지에 대한 검증도 중요하다
    @Test
    public void 회원_중복_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

//        try-catch 문으로도 가능
//        try {
//            memberService.join(member2);
//            fail(); // 여기까지 코드가 오면 예외처리 실패했다는 뜻
//        } catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

//        assertThrows로도 처리 가능
//        IllegalStateException e = assertThrows(IllegalStateException.class,
//                () -> memberService.join(member2));
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        우테코에서 배운 예외처리 검증법
        assertThatThrownBy(() -> memberService.join(member2))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}