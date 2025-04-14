package com.ho.diary.api.service.feed;

import com.ho.diary.domain.dto.feed.FeedDto;
import com.ho.diary.domain.entity.feed.Feed;
import com.ho.diary.domain.service.feed.FeedService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedApiService {
  private final FeedService feedService;

  public List<FeedDto> getFeeds() {
    return feedService.getFeeds()
      .stream().map(FeedDto::of)
      .toList();
  }

  public FeedDto getFeed(Long id, Long userId) {
    Feed feed = feedService.getFeed(id);
    if (!feed.getCreatedBy().equals(userId)) {
      feed.increaseViewCount();
    }
    return FeedDto.of(feed);
  }
}
