package com.playdata.springbootproject.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HelloController.class) // 특정 컨트롤러를 테스트하기 위한 설정
@ExtendWith(SpringExtension.class) // Spring Framework를 사용하는 테스트를 위한 설정
class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 컨트롤러를 테스트하기 위한 MockMvc 객체

    @Test
    void hello() throws Exception {
        String hello = "hello";

        // /hello 요청을 보내고 응답에 대한 기대 설정
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // HTTP 상태 코드가 200인지 검증
                .andExpect(content().string(hello)); // 응답 본문이 "hello"인지 검증
    }

    @Test
    public void helloDto() throws Exception {
        // given
        String name = "hello";
        int amount = 1000;

        // when, then
        // /hello/dto 요청을 보내고 응답에 대한 기대 설정
        mvc.perform(get("/hello/dto")
                .param("name", name) // API 테스트할 때 사용될 요청 파라미터 설정
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name)) // JSON 응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount").value(amount));
    }
}