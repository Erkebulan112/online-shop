package com.project_sql.online_shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @NonNull
  @Column(name = "name")
  private String name;

  @NonNull
  @Column(name = "description")
  private String description;

  @NonNull
  @Column(name = "price")
  private Integer price;

  @Column(name = "quantity")
  private Integer quantity;
}
