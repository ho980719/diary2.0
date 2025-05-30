package com.ho.diary.domain.entity.feed;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "hashtags")
@Entity
@Getter
@NoArgsConstructor
public class Hashtag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false, unique = true)
  private String name;

  public Hashtag(String name) {
    this.name = name;
  }
}
