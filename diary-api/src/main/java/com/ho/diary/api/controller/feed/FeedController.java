package com.ho.diary.api.controller.feed;

import com.ho.diary.api.controller.feed.dto.FeedCreateRequestDto;
import com.ho.diary.api.controller.feed.dto.FeedUpdateRequestDto;
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
  public ApiResult<Void> createFeed(@Valid FeedCreateRequestDto feedCreateRequestDto,
    @AuthenticationPrincipal UserPrincipal user) {
    feedApiService.createFeed(feedCreateRequestDto, user.getId());
    return ApiResult.ok();
  }

  @PutMapping("/{id}")
  public ApiResult<Void> updateFeed(
    @PathVariable(value = "id") Long id,
    @Valid FeedUpdateRequestDto feedUpdateRequestDto,
    @AuthenticationPrincipal UserPrincipal user) {
    feedApiService.updateFeed(id, feedUpdateRequestDto, user.getId());
    return ApiResult.ok();
  }

  @DeleteMapping("/{id}")
  public ApiResult<Void> deleteFeed(
    @PathVariable(value = "id") Long id,
    @AuthenticationPrincipal UserPrincipal user) {
    feedApiService.deleteFeed(id, user.getId());
    return ApiResult.ok();
  }
}

