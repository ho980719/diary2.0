package com.ho.diary.domain.entity;

import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @Column(name = "deleted", nullable = false)
  protected Boolean deleted = false;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  protected LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  protected LocalDateTime updatedAt;

  @Column(name = "deleted_at", nullable = false)
  protected LocalDateTime deletedAt;

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  protected Long createdBy;

  @LastModifiedBy
  @Column(name = "updated_by")
  protected Long updatedBy;

  @Column(name = "deleted_by")
  protected Long deletedBy;

  /**
   * 공통 삭제 함수
   * @param deletedBy
   * @throws org.springframework.web.client.HttpClientErrorException.BadRequest
  */
  public void delete(Long deletedBy) {
    if (deletedBy == null) {
      throw new BusinessException(ErrorCode.MISSING_REQUEST_PARAMETER);
    }
    this.deleted = true;
    this.deletedBy = deletedBy;
    this.deletedAt = LocalDateTime.now();
  }
}

