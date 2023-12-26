package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 컨트롤러임을 명시하는 어노테이션
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name", required = false) String name, Model model){
        // @RequestParam("name") String name // 이런식으로 쓰지만 위처럼 쓰면 get 요청시 뒤에 name에 대한 파라미터를 안붙여도 된다.
        // required의 default 값은 true이다. // 즉 파라마터를 넘겨줘야한다.
        model.addAttribute("name", name);
        return "hello-template";
    }

}
