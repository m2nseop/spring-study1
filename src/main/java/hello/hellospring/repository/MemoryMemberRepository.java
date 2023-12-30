package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// 리포지지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
// 일단은 디비 대체
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    // 공유되는 변수를 다룰때는 동시성 문제를 해결하기 위해 ConcurrentHashMap을 사용한다.
    private static long sequence = 0L;
    // auto increment 대체
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 선증가후 member 멤버변수 id에 sequence 저장
        store.put(member.getId(), member); // id : member 로 Map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // store에서 id로 꺼낸 member객체가 빈 객체일 수 있으니 이 또한 optional로 감싼다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store저장된 value들을 탐색한다
                .filter(member -> member.getName().equals(name)) // 함수 파라미터로 넘어온 name과 같은 name을 가진 Member를 필터링한다.
                .findAny(); // 하나라도 찾으면 반환한다. 없으면 optional에 감싸져서 Null로 반횐된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store의 멤버들을 List형태로 반환한다.
    }

    public void clearStore(){
        store.clear();
    }
}
