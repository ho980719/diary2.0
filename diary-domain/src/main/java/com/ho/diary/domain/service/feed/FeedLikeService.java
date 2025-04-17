package com.ho.diary.domain.service.feed;

import com.ho.diary.domain.repository.feed.FeedLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedLikeService {
  private final FeedLikeRepository feedLikeRepository;

  @Transactional
  public void feedLike(Long feedId) {
  }
}
