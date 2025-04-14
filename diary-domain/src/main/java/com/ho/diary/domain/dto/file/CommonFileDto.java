package com.ho.diary.domain.dto.file;

import com.ho.diary.domain.entity.file.CommonFile;

public record CommonFileDto(String fileKey, String originName) {
  public static CommonFileDto from(CommonFile file) {
    return new CommonFileDto(file.getFileKey(), file.getOriginName());
  }
}
