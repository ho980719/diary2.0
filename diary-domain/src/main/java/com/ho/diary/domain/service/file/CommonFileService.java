package com.ho.diary.domain.service.file;

import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import com.ho.diary.core.file.FileManager;
import com.ho.diary.core.file.dto.FileDto;
import com.ho.diary.domain.entity.file.CommonFile;
import com.ho.diary.domain.entity.file.enums.FileReferenceType;
import com.ho.diary.domain.repository.file.CommonFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonFileService {
  private final CommonFileRepository commonFileRepository;
  private final FileManager fileManager;

  @Transactional(readOnly = true)
  public List<CommonFile> getFiles(Long refId, FileReferenceType refType) {
    return commonFileRepository.findByReferenceIdAndReferenceType(refId, refType);
  }

  public CommonFile uploadFile(MultipartFile file, Long refId, FileReferenceType refType) {
    String path = refType.name().toLowerCase();
    String fileKey = fileManager.generateFileKey(path);
    try {
      FileDto fileDto = fileManager.save(file, fileKey, path);
      CommonFile entity = CommonFile.of(fileDto, refId, refType);
      return commonFileRepository.save(entity);
    } catch (Exception e) {
      log.error("file save error", e);
      throw new BusinessException(ErrorCode.FILE_UPLOAD_ERROR);
    }
  }

  public void deleteFile(CommonFile file) {
    fileManager.delete(file.getStoredName());
    commonFileRepository.delete(file);
  }
}
