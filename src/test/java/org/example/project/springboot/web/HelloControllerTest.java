package org.example.project.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@RunWith(SpringRunner.class)
// @Controller 사용 가능, @Service, @Component, @Repository 사용 불가
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // 스프링이 관리하는 빈을 주입 받음
    @Autowired
    // 스프링 mvc 테스트의 시작점
    // get, post 등에 대한 api 테스트
    private MockMvc mvc;

    @Test
    public void hello_return() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                // http 헤더의 상태코드 검증 (200 코드)
                .andExpect(status().isOk())
                // 본문 내용 검증 (hello)
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto_return() throws Exception {
        String name = "name";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                    // api 테스트시 사용될 요청 파라미터 설정
                    // 값은 string만 허용
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // JSON 응닶값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
