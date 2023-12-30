package hello.hellospring.domain;


// 도메인 : 비즈니스 도메인 객체
// 주로 데이터베이스에 저장하고 관리됨

public class Member {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
