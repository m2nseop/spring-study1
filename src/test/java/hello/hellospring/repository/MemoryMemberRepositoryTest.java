package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

// MemoryMemberRepository 클래스 테스트 코드
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // @Test 함수들은 실행 순서가 랜덤이며, 서로 의존해서는 안된다.
    // @AfterEach는 @Test 함수가 끝날 때 마다 실행된다.
    // @Test 함수들은 repository내의 필드변수 객체를 동시에 사용하기 때문에 매번 clear 해줘야한다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    // 나와야만 하는 값이 expected, 즉 예상되는 값
    // 내가 만든 로직에 의해 나온 값이 actual, 즉 실제 값
    // Assertions.assertThat(actual).isEqualTo(expected);
    // 나는 주장한다 "실제 값"이 "기대 값"과 같은 것이라고
    @Test
    public void save(){
        Member expected = new Member();
        expected.setName("spring1");

        repository.save(expected);

        Member actual = repository.findById(expected.getId()).get();
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findByName(){
        Member expected1 = new Member();
        expected1.setName("spring1");
        repository.save(expected1);

        Member expected2 = new Member();
        expected2.setName("spring2");
        repository.save(expected2);

        Member actual1 = repository.findByName(expected1.getName()).get();
        Member actual2 = repository.findByName(expected2.getName()).get();

        Assertions.assertThat(actual1).isEqualTo(expected1);
        Assertions.assertThat(actual2).isEqualTo(expected2);
        Assertions.assertThat(actual1).isNotEqualTo(expected2);
    }

    @Test
    public void findAll(){
        Member expected1 = new Member();
        expected1.setName("spring1");
        repository.save(expected1);

        Member expected2 = new Member();
        expected2.setName("spring2");
        repository.save(expected2);

        Assertions.assertThat(repository.findAll().size()).isEqualTo(2);
        Assertions.assertThat(repository.findAll().size()).isNotEqualTo(3);
    }
}
