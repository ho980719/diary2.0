package com.ho.diary.domain.repository.feed;

import com.ho.diary.domain.entity.feed.FeedLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedLikeRepository extends JpaRepository<FeedLike, Long> {

}
