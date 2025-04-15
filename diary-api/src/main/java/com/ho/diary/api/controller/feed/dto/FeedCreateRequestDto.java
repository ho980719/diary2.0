package com.ho.diary.api.controller.feed.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FeedCreateRequestDto {
  @NotNull(message = "내용을 입력해주세요.")
  private String content;
  List<MultipartFile> images = new ArrayList<>();
}
