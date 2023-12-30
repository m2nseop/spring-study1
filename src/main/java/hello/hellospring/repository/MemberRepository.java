package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.Optional;
import java.util.List;

public interface MemberRepository {

    Member save(Member member); // 회원 저장 후 저장한 회원 반환
    Optional<Member> findById(Long id); // id로 회원 정보 찾기 // null반환을 감안한 optional
    Optional<Member> findByName(String name); // name으로 "
    List<Member> findAll(); // db에 저장된 모든 회원 정보 가져오기
}
