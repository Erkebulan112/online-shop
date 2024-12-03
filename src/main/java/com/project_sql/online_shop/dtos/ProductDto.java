package com.project_sql.online_shop.dtos;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
  private String name;
  private String description;
  private Integer price;
  private Integer quantity;
}
