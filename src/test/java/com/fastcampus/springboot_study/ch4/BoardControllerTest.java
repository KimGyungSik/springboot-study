package com.fastcampus.springboot_study.ch4;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fastcampus.springboot_study.SpringbootStudyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@WebMvcTest(SpringbootStudyApplication.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //    @GetMapping("/hello2")
    //    @ResponseBody
    //    public String hello(String name) {
    //        return "Hello="+name;
    //    }

    @Test
    public void testHello() throws Exception {
        // 1. 조건(given)
        // 2. 테스트 실행(run test)
        mockMvc.perform(get("/hello2").param("name","aaa"))
                // 3. 검증(assertion)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello=aaa"))
                .andDo(print());
    }
}
