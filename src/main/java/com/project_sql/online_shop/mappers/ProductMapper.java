package com.project_sql.online_shop.mappers;

import com.project_sql.online_shop.dtos.ProductDto;
import com.project_sql.online_shop.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
}
