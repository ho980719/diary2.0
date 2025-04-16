package com.ho.diary.domain.entity.feed;

import com.ho.diary.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post_likes")
@AttributeOverrides({@AttributeOverride(name = "deleted", column = @Column(name = "deleted")),
  @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
  @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
  @AttributeOverride(name = "deletedAt", column = @Column(name = "deleted_at"))})
public class FeedLike extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "post_likes_id_gen")
  @SequenceGenerator(name = "post_likes_id_gen", sequenceName = "post_likes_id_seq",
    allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "post_id", nullable = false)
  private Integer postId;

}