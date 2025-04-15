package com.ho.diary.api.service.feed;

import com.ho.diary.api.controller.feed.dto.FeedCreateRequestDto;
import com.ho.diary.api.controller.feed.dto.FeedUpdateRequestDto;
import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import com.ho.diary.domain.dto.feed.FeedDto;
import com.ho.diary.domain.dto.file.CommonFileDto;
import com.ho.diary.domain.entity.feed.Feed;
import com.ho.diary.domain.entity.file.enums.FileReferenceType;
import com.ho.diary.domain.entity.user.User;
import com.ho.diary.domain.service.feed.FeedService;
import com.ho.diary.domain.service.file.CommonFileService;
import com.ho.diary.domain.service.user.UserService;
import jakarta.validation.Valid;
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
  public void createFeed(FeedCreateRequestDto feedCreateRequestDto, Long userId) {
    User user = userService.getUser(userId);

    Feed feed = feedService.createFeed(Feed.builder()
      .user(user)
      .content(feedCreateRequestDto.getContent())
      .build()
    );

    if (!feedCreateRequestDto.getImages().isEmpty()) {
      feedCreateRequestDto.getImages()
        .forEach(x -> commonFileService.uploadFile(x, feed.getId(), REFERENCE_TYPE));
    }
  }

  @Transactional
  public void updateFeed(Long id, @Valid FeedUpdateRequestDto feedUpdateRequestDto, Long updatedBy) {
    Feed feed = feedService.getFeed(id);
    if (!feed.getUser().getId().equals(updatedBy)) {
      throw new BusinessException(ErrorCode.ACCESS_DENIED);
    }
    feed.update(feedUpdateRequestDto.content());
  }


  @Transactional
  public void deleteFeed(Long id, Long deletedBy) {
    Feed feed = feedService.getFeed(id);
    if (!feed.getUser().getId().equals(deletedBy)) {
      throw new BusinessException(ErrorCode.ACCESS_DENIED);
    }
    feed.delete(deletedBy);
  }

}
