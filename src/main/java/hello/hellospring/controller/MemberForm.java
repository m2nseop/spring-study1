package hello.hellospring.controller;

public class MemberForm {
    private String name; // html input태그의 name속성의 값과 매칭된다.
    // 만약 name속성값이 id였으면 변수도 id로 설정해야한다.

    public String getName() {
        return name;
    }

    // form을 post방식으로 컨트롤러에 넘기면 스프링은 setter를 통해
    // html에서 입력했던 값으로 name의 값을 설정한다.
    public void setName(String name) {
        this.name = name;
    }
}
