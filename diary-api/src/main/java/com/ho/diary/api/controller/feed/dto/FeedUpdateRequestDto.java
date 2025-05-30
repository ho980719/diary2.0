package com.ho.diary.api.controller.feed.dto;

import com.ho.diary.domain.dto.file.CommonFileDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FeedUpdateRequestDto(
  @NotNull(message = "내용을 입력해주세요.") String content,
  List<CommonFileDto> images,
  List<String> hashtags
) {
}
