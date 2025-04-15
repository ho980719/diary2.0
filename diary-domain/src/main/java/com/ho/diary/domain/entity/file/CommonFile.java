package com.ho.diary.domain.entity.file;

import com.ho.diary.core.file.dto.FileDto;
import com.ho.diary.domain.entity.file.enums.FileReferenceType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "common_file")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonFile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "file_key", nullable = false, unique = true, updatable = false)
  private String fileKey;

  @Column(name = "origin_name", length = 255)
  private String originName;

  @Column(name = "stored_name", length = 255)
  private String storedName;

  @Column(name = "file_path", length = 512)
  private String filePath;

  @Column(name = "file_size")
  private Long fileSize;

  @Column(name = "content_type", length = 100)
  private String contentType;

  @Column(name = "reference_id")
  private Long referenceId;

  @Column(name = "reference_type")
  @Enumerated(EnumType.STRING)
  private FileReferenceType referenceType;

  @Column(name = "deleted")
  private boolean deleted = false;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  public static String generateFileKey() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  private static String getExtension(String filename) {
    if (filename == null) return null;
    int lastIndex = filename.lastIndexOf('.');
    return (lastIndex != -1) ? filename.substring(lastIndex + 1) : null;
  }

  public static CommonFile of(FileDto file, Long refId, FileReferenceType type) {
//    String storedFilename = type.name().toLowerCase() + "_" + UUID.randomUUID().toString().replace("-", "");
    return CommonFile.builder()
      .fileKey(file.fileKey())
      .filePath(file.path())
      .originName(file.originName())
      .storedName(file.storedName())
      .fileSize(file.fileSize())
      .contentType(file.contentType())
      .referenceId(refId)
      .referenceType(type)
      .build();
  }
}