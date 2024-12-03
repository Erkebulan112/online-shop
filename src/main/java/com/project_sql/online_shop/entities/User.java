package com.project_sql.online_shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @NonNull
  @Column(name = "username")
  private String username;

  @NonNull
  @Column(name = "login")
  private String login;

  @NonNull
  @Column(name = "password")
  private String password;
}
