package com.ho.diary.domain.service.feed;

import com.ho.diary.domain.dto.feed.FeedDto;
import com.ho.diary.domain.repository.feed.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
  private final FeedRepository feedRepository;

  public List<FeedDto> getFeeds() {
    return feedRepository.findAll()
      .stream().map(FeedDto::of)
      .toList();
  }
}
