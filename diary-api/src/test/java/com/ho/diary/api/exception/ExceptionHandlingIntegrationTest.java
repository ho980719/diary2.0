package com.ho.diary.api.exception;

import com.ho.diary.api.controller.TestExceptionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ExceptionHandlingIntegrationTest.TestControllerConfig.class)
class ExceptionHandlingIntegrationTest {

  @TestConfiguration
  static class TestControllerConfig {
    @Bean
    public TestExceptionController testExceptionController() {
      return new TestExceptionController();
    }
  }

  @Autowired
  MockMvc mockMvc;

  @Test
  void globalExceptionTest() throws Exception {
    mockMvc.perform(get("/api/test-exception"))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.code", is("INVALID_INPUT_VALUE")))
      .andExpect(jsonPath("$.message", is("잘못된 입력입니다.")));
  }
}