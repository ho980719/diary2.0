package com.ho.diary.domain.repository.feed;

import com.ho.diary.domain.entity.feed.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, String> {

  List<Hashtag> findByNameIn(Collection<String> names);
}
