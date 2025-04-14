package com.ho.diary.api.controller.feed;

import com.ho.diary.api.service.feed.FeedApiService;
import com.ho.diary.core.response.ApiResult;
import com.ho.diary.domain.dto.feed.FeedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feeds")
@RequiredArgsConstructor
public class FeedController {
  private final FeedApiService feedApiService;

  @GetMapping
  public ApiResult<List<FeedDto>> getFeeds() {
    return ApiResult.ok(feedApiService.getFeeds());
  }
}

