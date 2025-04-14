package com.ho.diary.api.controller.feed;

import com.ho.diary.api.controller.feed.dto.FeedRequestDto;
import com.ho.diary.api.service.feed.FeedApiService;
import com.ho.diary.auth.security.dto.UserPrincipal;
import com.ho.diary.core.response.ApiResult;
import com.ho.diary.domain.dto.feed.FeedDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/{id}")
  public ApiResult<FeedDto> getFeed(@PathVariable(value = "id") Long id,
    @AuthenticationPrincipal UserPrincipal user) {
    return ApiResult.ok(feedApiService.getFeed(id, user.getId()));
  }

  @PostMapping
  public ApiResult<Void> createFeed(@RequestBody @Valid FeedRequestDto feedRequestDto,
    @AuthenticationPrincipal UserPrincipal user) {
    feedApiService.createFeed(feedRequestDto, user.getId());
    return ApiResult.ok();
  }
}

