package com.ho.diary.core.file;

import com.ho.diary.core.file.dto.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class FileManager {

  @Value("${file.upload-dir}")
  private String uploadDir;

  public String generateFileKey(String prefix) {
    return prefix + "_" + UUID.randomUUID().toString().replace("-", "");
  }

  public FileDto save(MultipartFile multipartFile, String fileKey, String path) throws IOException {
    if (path == null || path.isEmpty()) {
      path = "temp";
    }

    if (path.startsWith(File.separator)) {
      path = path.substring(1);
    }
    File file = new File(uploadDir + File.separator + path + File.separator + fileKey);
    file.getParentFile().mkdirs();

    multipartFile.transferTo(file);
    log.info("Saved file: {}", file.getAbsolutePath());
    return new FileDto(fileKey, file.getAbsolutePath(), multipartFile.getOriginalFilename(), file.getName(),
      multipartFile.getSize(), multipartFile.getContentType());
  }

  public void delete(String storedName) {
    File file = new File(uploadDir + File.separator + storedName);
    if (file.exists()) {
      file.delete();
      log.info("Deleted file: {}", file.getAbsolutePath());
    }
  }
}
