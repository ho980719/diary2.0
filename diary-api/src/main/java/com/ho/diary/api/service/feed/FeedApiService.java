package com.ho.diary.api.service.feed;

import com.ho.diary.api.controller.feed.dto.FeedCreateRequestDto;
import com.ho.diary.api.controller.feed.dto.FeedUpdateRequestDto;
import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import com.ho.diary.domain.dto.feed.FeedDto;
import com.ho.diary.domain.dto.file.CommonFileDto;
import com.ho.diary.domain.entity.feed.Feed;
import com.ho.diary.domain.entity.feed.Hashtag;
import com.ho.diary.domain.entity.file.enums.FileReferenceType;
import com.ho.diary.domain.entity.user.User;
import com.ho.diary.domain.service.feed.FeedService;
import com.ho.diary.domain.service.feed.HashtagService;
import com.ho.diary.domain.service.file.CommonFileService;
import com.ho.diary.domain.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FeedApiService {
  private final static FileReferenceType REFERENCE_TYPE = FileReferenceType.FEED;

  private final FeedService feedService;
  private final HashtagService hashtagService;
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

    Set<Hashtag> hashtags = createHashtags(feedCreateRequestDto.getHashtags());

    Feed feed = feedService.createFeed(Feed.builder()
      .user(user)
      .content(feedCreateRequestDto.getContent())
      .hashtags(hashtags)
      .build()
    );

    if (!feedCreateRequestDto.getImages().isEmpty()) {
      feedCreateRequestDto.getImages()
        .forEach(x -> commonFileService.uploadFile(x, feed.getId(), REFERENCE_TYPE));
    }
  }

  @Transactional
  protected Set<Hashtag> createHashtags(List<String> requestHashtags) {
    // 1. 기존 hashtag 조회
    List<Hashtag> existingHashtags = hashtagService.findByNameIn(requestHashtags);

    // 2. 존재하지 않는 이름만 필터링하여 새로 생성
    Set<String> existingNames = existingHashtags.stream()
      .map(Hashtag::getName)
      .collect(Collectors.toSet());

    // 3. 없는 hashtag save
    List<Hashtag> toCreate = requestHashtags.stream()
      .filter(name -> !existingNames.contains(name))
      .distinct()
      .map(Hashtag::new)
      .toList();
    List<Hashtag> newHashtags = hashtagService.saveAll(toCreate);

    // 4. merge return
    return Stream.concat(existingHashtags.stream(), newHashtags.stream())
      .collect(Collectors.toSet());
  }

  @Transactional
  public void updateFeed(Long id, @Valid FeedUpdateRequestDto feedUpdateRequestDto, Long updatedBy) {
    Feed feed = feedService.getFeed(id);
    if (!feed.getUser().getId().equals(updatedBy)) {
      throw new BusinessException(ErrorCode.ACCESS_DENIED);
    }

    Set<Hashtag> hashtags = updateHashtags(feed, feedUpdateRequestDto.hashtags());

    feed.update(feedUpdateRequestDto.content());
  }

  @Transactional
  protected Set<Hashtag> updateHashtags(Feed feed, List<String> requestHashtags) {
    Set<Hashtag> newHashtags = createHashtags(requestHashtags);

    // 기존과 비교하여 차이만 반영
    Set<Hashtag> currentHashtags = feed.getHashtags();

    Set<Hashtag> toAdd = newHashtags.stream()
      .filter(ht -> !currentHashtags.contains(ht))
      .collect(Collectors.toSet());

    Set<Hashtag> toRemove = currentHashtags.stream()
      .filter(ht -> !newHashtags.contains(ht))
      .collect(Collectors.toSet());

    currentHashtags.addAll(toAdd);
    currentHashtags.removeAll(toRemove);

    // @todo: 업데이트 로직 추가 필요
//    // 7. 내용도 업데이트
//    post.setContent(dto.getContent());
//
//    // 8. 저장
//    feedPostRepository.save(post); // 변경 감지로 update 수행됨

    return null;
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
