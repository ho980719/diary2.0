package com.ho.diary.api.controller;

import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestExceptionController {

  @GetMapping("/api/test-exception")
  public String throwBusinessException() {
    throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
  }
}