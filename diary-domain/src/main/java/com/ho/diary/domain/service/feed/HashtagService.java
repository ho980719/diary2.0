package com.ho.diary.domain.service.feed;

import com.ho.diary.domain.entity.feed.Hashtag;
import com.ho.diary.domain.repository.feed.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashtagService {
  private final HashtagRepository hashtagRepository;

  public List<Hashtag> findByNameIn(List<String> array) {
    return hashtagRepository.findByNameIn(array);
  }

  public List<Hashtag> saveAll(List<Hashtag> hashtags) {
    return hashtagRepository.saveAll(hashtags);
  }
}
