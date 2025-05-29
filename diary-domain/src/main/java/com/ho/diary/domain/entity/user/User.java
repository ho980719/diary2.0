package com.ho.diary.domain.entity.user;

import com.ho.diary.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(unique = true, length = 100)
  private String email;

  @Column(nullable = false)
  private Boolean enabled = true;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();

  @Builder
  public User(String username, String password, String email, Set<Role> roles) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.roles = roles;
  }
}
