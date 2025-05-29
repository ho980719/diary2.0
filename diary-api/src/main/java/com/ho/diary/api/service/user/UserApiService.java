package com.ho.diary.api.service.user;

import com.ho.diary.api.controller.auth.dto.SignUpRequest;
import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import com.ho.diary.domain.entity.user.User;
import com.ho.diary.domain.entity.user.enums.RoleType;
import com.ho.diary.domain.service.user.RoleService;
import com.ho.diary.domain.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserApiService {
  private final UserService userService;
  private final RoleService roleService;

  @Transactional
  public void signUp(SignUpRequest request) {
    if (userService.duplicateUsername(request.username())) {
      throw new BusinessException(ErrorCode.DUPLICATE_USERNAME);
    }
    if (userService.duplicateEmail(request.email())) {
      throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
    }

    User user = userService.createUser(
      User.builder()
        .username(request.username())
        .password(request.password())
        .email(request.email())
        .roles(Set.of(roleService.getRole(RoleType.ROLE_USER)))
        .build()
    );
  }
}
