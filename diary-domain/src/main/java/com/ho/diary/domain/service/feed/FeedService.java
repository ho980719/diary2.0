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
    return feedRepository.findAll();
  }

  public Feed getFeed(Long id) {
    return feedRepository.findById(id)
      .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND));
  }
}
