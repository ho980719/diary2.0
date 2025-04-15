package com.ho.diary.core.file.dto;

public record FileDto(String fileKey, String path, String originName, String storedName, Long fileSize, String contentType) {
  public FileDto(String fileKey, String path, String originName, String storedName, Long fileSize,
    String contentType) {
    this.fileKey = fileKey;
    this.path = path;
    this.originName = originName;
    this.storedName = storedName;
    this.fileSize = fileSize;
    this.contentType = contentType;
  }
}
