package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 핵심 비즈니스 로직 구현
@Service
public class MemberService {

    // command + shift + T -> 해당 클래스의 테스트 클래스를 package까지 만들어줌;;

    private final MemberRepository memberRepository;
    // java interface에 대해서 좀 알아볼 것

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); // command + shift + M -> 특정 코드 함수로 추출
        // 검증 후 문제 없으면 회원 정보 저장
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // command + shift + M -> 특정 코드 함수로 추출
        Optional<Member> result = memberRepository.findByName(member.getName());

        result.ifPresent(m -> { // 만약 같은 이름이 있는 회원이 있다면? 에 대한 예외처리
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        // ifPresent는 result를 Optional로 감쌌기 때문에 사용할 수 있는 함수이다.
        // Optional을 사용하지 않았다면 if(member != null){예외처리} 이런식으로 작성했었을 것

//        Member result = result.get(); 이런식으로 값을 꺼낼 수 있다만 권장하지만 않는다.
//        Member result = result.orElseGet(); 이런식으로 값이 없을 경우에 대한 예외처리도 할 수 있는 orElseGet을 많이 쓴다고 한다.

//        memberRepository.findByName(member.getName());의 리턴값은 Optional<Member>이므로

//        memberRepository.findByName(member.getName())
//            .ifPresent(m -> {
//                throw new IllegalArgumentException("이미 존재하는 회원입니다.");
//            });
//        위 처럼도 작성 가능
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
