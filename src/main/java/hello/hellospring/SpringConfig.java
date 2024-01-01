package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Service, @Repository 어노테이션을 통해 컴포넌트 스캔을 통한 자동 의존 관계 설정을 하는 방법 대신에
// @Configuration 어노테이션을 통해 직접 빈을 등록한다.
@Configuration
public class SpringConfig {

    // @Bean 어노테이션으로 빈을 등록한다. // 컨테이너에 객체를 생성한다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    // 이렇게 작성할 경우 MemoryM
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
