package com.ho.diary.domain.service.user;

import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import com.ho.diary.domain.entity.user.Role;
import com.ho.diary.domain.entity.user.enums.RoleType;
import com.ho.diary.domain.repository.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
  private final RoleRepository roleRepository;

  public Role getRole(RoleType type) {
    return roleRepository.findByRoleType(type)
      .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND));
  }
}
