package com.ho.diary.api.service.feed;

import com.ho.diary.api.controller.feed.dto.FeedRequestDto;
import com.ho.diary.domain.dto.feed.FeedDto;
import com.ho.diary.domain.dto.file.CommonFileDto;
import com.ho.diary.domain.entity.feed.Feed;
import com.ho.diary.domain.entity.file.enums.FileReferenceType;
import com.ho.diary.domain.entity.user.User;
import com.ho.diary.domain.service.feed.FeedService;
import com.ho.diary.domain.service.file.CommonFileService;
import com.ho.diary.domain.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedApiService {
  private final static FileReferenceType REFERENCE_TYPE = FileReferenceType.FEED;

  private final FeedService feedService;
  private final CommonFileService commonFileService;
  private final UserService userService;

  @Transactional(readOnly = true)
  public List<FeedDto> getFeeds() {
    return feedService.getFeeds()
      .stream().map(x -> {
        List<CommonFileDto> images = commonFileService.getFiles(x.getId(), REFERENCE_TYPE)
          .stream().map(CommonFileDto::from)
          .toList();
        return FeedDto.of(x, images);
      })
      .toList();
  }

  @Transactional(readOnly = true)
  public FeedDto getFeed(Long id, Long userId) {
    Feed feed = feedService.getFeed(id);
    if (!feed.getCreatedBy().equals(userId)) {
      feed.increaseViewCount();
    }

    List<CommonFileDto> images = commonFileService.getFiles(id, REFERENCE_TYPE)
      .stream().map(CommonFileDto::from)
      .toList();
    return FeedDto.of(feed, images);
  }

  @Transactional
  public void createFeed(FeedRequestDto feedRequestDto, Long userId) {
    if (!feedRequestDto.getFiles().isEmpty()) {
      feedRequestDto.getFiles()
        .forEach(x -> commonFileService.uploadFile(x, 0L, REFERENCE_TYPE));
    }

    User user = userService.getUser(userId);

    feedService.createFeed(Feed.builder()
      .user(user)
      .content(feedRequestDto.getContent())
      .build()
    );
//    feedService.createFeed(new Feed(user, feedRequestDto.getContent()));
  }
}
