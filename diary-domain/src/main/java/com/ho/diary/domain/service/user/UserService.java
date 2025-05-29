package com.ho.diary.domain.service.user;

import com.ho.diary.core.exception.BusinessException;
import com.ho.diary.core.exception.enums.ErrorCode;
import com.ho.diary.domain.entity.user.User;
import com.ho.diary.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User getUser(Long id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND));

    if (Boolean.TRUE.equals(user.getDeleted())) {
      throw new BusinessException(ErrorCode.RESOURCE_NOT_FOUND);
    }

    if (Boolean.FALSE.equals(user.getEnabled())) {
      throw new BusinessException(ErrorCode.DISABLED_USER);
    }

    return user;
  }

  public boolean duplicateUsername(String username) {
    return userRepository.existsUserByUsername(username);
  }

  public boolean duplicateEmail(String email) {
    return userRepository.existsUserByEmail(email);
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }
}
