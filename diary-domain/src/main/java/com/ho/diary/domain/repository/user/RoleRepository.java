package com.ho.diary.domain.repository.user;

import com.ho.diary.domain.entity.user.Role;
import com.ho.diary.domain.entity.user.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByRoleType(RoleType type);
}
