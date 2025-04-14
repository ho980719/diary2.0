package com.ho.diary.domain.service.file;

import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import com.ho.diary.core.file.FileManager;
import com.ho.diary.domain.entity.file.CommonFile;
import com.ho.diary.domain.entity.file.enums.FileReferenceType;
import com.ho.diary.domain.repository.file.CommonFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CommonFileService {
  private final CommonFileRepository commonFileRepository;
  private final FileManager fileManager;

  public CommonFile uploadFile(MultipartFile file, Long refId, FileReferenceType refType) {
    String path = refType.name().toLowerCase();
    String fileKey = fileManager.generateFileKey(path);
    try {
      fileManager.save(file, fileKey, path);
    } catch (Exception e) {
      throw new BusinessException(ErrorCode.FILE_UPLOAD_ERROR);
    }

    CommonFile entity = CommonFile.of(file, refId, refType);
    return commonFileRepository.save(entity);
  }

  public void deleteFile(CommonFile file) {
    fileManager.delete(file.getStoredName());
    commonFileRepository.delete(file);
  }
}
