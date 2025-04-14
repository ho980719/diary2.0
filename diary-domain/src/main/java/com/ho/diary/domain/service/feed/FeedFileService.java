package com.ho.diary.domain.service.feed;

import com.ho.diary.domain.entity.file.enums.FileReferenceType;
import com.ho.diary.domain.service.file.CommonFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedFileService {
  private final CommonFileService commonFileService;

  private final static FileReferenceType REFERENCE_TYPE = FileReferenceType.FEED;
}
