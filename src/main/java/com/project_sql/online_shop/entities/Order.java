package com.project_sql.online_shop.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "order_date", nullable = false)
  private LocalDateTime orderDate;

  @Column(name = "status", nullable = false)
  private String status;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @NonNull
  private User user;
}
