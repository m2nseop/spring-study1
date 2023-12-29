package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @GetMapping("hello-string")
    @ResponseBody
    // 해당 어노테이션을 붙여주어야 spring은 api를 처리하는 컨트롤러임을 인지한다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // "hello 파라미터값"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);

        // Spring은 @ResponseBody 어노테이션을 보고 api요청임을 감지
        // 리턴되는 데이터가 객체이므로 HttepMessageConverter중 JsonConverter(디폴트는 MappingJackson2HttpMessageConverter)를 이용해 객체를 json으로 변환하여
        // http 응답 body에 싣고 웹브라우저에 반환
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
