package com.ho.diary.domain.entity.feed;

import com.ho.diary.domain.entity.BaseEntity;
import com.ho.diary.domain.entity.file.CommonFile;
import com.ho.diary.domain.entity.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@Table(name = "feed_posts")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Feed extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "content", columnDefinition = "TEXT")
  private String content;

  @Transient
  private List<CommonFile> images = new ArrayList<>();


  private Long viewCount = 0L;

  public void increaseViewCount() {
    this.viewCount++;
  }

  @Builder
  public Feed(User user, String content) {
    this.user = user;
    this.content = content;
  }
}
