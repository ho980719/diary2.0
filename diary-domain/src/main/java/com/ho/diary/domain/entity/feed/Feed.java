package com.ho.diary.domain.entity.feed;

import com.ho.diary.domain.entity.BaseEntity;
import com.ho.diary.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@Table(name = "feed_posts")
public class Feed extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "content", columnDefinition = "TEXT")
  private String content;

  @Column(name = "image_urls", columnDefinition = "TEXT[]", nullable = false)
  @ElementCollection
  private List<String> imageUrls = new ArrayList<>();
}
