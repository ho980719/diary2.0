package com.ho.diary.api.service.feed;

import com.ho.diary.domain.dto.feed.FeedDto;
import com.ho.diary.domain.service.feed.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedApiService {
  private final FeedService feedService;

  public List<FeedDto> getFeeds() {
    return feedService.getFeeds();
  }
}
