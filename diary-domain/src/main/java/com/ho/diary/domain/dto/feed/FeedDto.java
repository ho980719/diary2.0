package com.ho.diary.domain.dto.feed;

import com.ho.diary.domain.entity.feed.Feed;
import com.ho.diary.domain.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FeedDto {
  private Long id;
  private String content;

  private String userName;
  private Long createdBy;
  private LocalDateTime createdAt;
  private int linkCount;

  @Builder
  public FeedDto(Long id, String content, String userName, Long createdBy,
    LocalDateTime createdAt, int linkCount) {
    this.id = id;
    this.content = content;
    this.userName = userName;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.linkCount = linkCount;
  }

  public static FeedDto of(Feed feed) {
    User user = feed.getUser();
    return FeedDto.builder()
      .id(feed.getId())
      .content(feed.getContent())
      .createdAt(feed.getCreatedAt())
      .createdBy(feed.getCreatedBy())
      .userName(user.getUsername())
      .linkCount(0)
      .build();
  }

}
