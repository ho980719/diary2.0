package com.ho.diary.domain.repository.file;

import com.ho.diary.domain.entity.file.CommonFile;
import com.ho.diary.domain.entity.file.enums.FileReferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonFileRepository extends JpaRepository<CommonFile, Long> {

  List<CommonFile> findByReferenceIdAndReferenceType(Long referenceId, FileReferenceType referenceType);

  void deleteByReferenceIdAndReferenceType(Long referenceId, FileReferenceType referenceType);
}
