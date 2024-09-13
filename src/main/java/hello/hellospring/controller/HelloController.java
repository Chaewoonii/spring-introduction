package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // @RequestParam(name="name", required=false): required 가 false 면 값을 꼭 넘기지 않아도 된다. 기본적으로 true이기 때문에 기본적으론 값을 넘겨야 함.
    // 파라미터 넘기는 방식: /hello-mvc?name=spring! -> spring!이 name으로 넘어간다.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // @ResponseBody html의 body 부분(<body>여기</body>)에 직접 데이터를 넣어 응답하겠다.
    // (소스보기를 통해 확인해보면 html 태그가 없고, 문자만 있음.)
    // ResponseBody 태그가 없으면 viewResolver가 템플릿을 찾고, 태그가 있으면 HttpMessageConverter가 동작한다.
    // 기본 문자처리는 StringHttpMessageConverter가 동작
    // 기본 객체처리는 MappingJackson2HttpMessageConverter가 동작
    // 객체를 json으로 매핑해주는 라이브러리는 Jackson과 Gson이 있는데, 스프링은 Jackson을 선택
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 클래스 자체로 넘기기 위해 api 방식을 사용.
        // 객체 자체를 넘기게 되면 json 형식으로 데이터가 넘어간다.
    }

    static class Hello{ // static 클래스는 클래스 내부에서 선언해도 바로 사용할 수 있다.
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
