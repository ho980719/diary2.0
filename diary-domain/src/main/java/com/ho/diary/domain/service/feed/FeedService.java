package com.ho.diary.domain.service.feed;

import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import com.ho.diary.domain.entity.feed.Feed;
import com.ho.diary.domain.repository.feed.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
  private final FeedRepository feedRepository;

  public List<Feed> getFeeds() {
    return feedRepository.findAll().stream()
      .filter(x -> !x.getDeleted())
      .toList();
  }

  public Feed getFeed(Long id) {
    return feedRepository.findById(id)
      .filter(x -> !x.getDeleted())
      .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND));
  }

  public Feed createFeed(Feed feed) {
    return feedRepository.save(feed);
  }
}
