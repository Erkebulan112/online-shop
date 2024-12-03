package com.project_sql.online_shop.services;

import com.project_sql.online_shop.dtos.ProductDto;
import com.project_sql.online_shop.entities.Product;
import com.project_sql.online_shop.mappers.ProductMapper;
import com.project_sql.online_shop.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public List<ProductDto> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream()
        .map(productMapper::toDto)
        .toList();
  }

  public ProductDto getProductById(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
    return productMapper.toDto(product);
  }

  @Transactional
  public ProductDto addProduct(ProductDto productDto) {
    Product product = productMapper.toEntity(productDto);
    Product newProduct = productRepository.save(product);
    return productMapper.toDto(newProduct);
  }

  @Transactional
  public ProductDto updateProduct(Long id, ProductDto productDto) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
    product.setName(productDto.getName());
    product.setPrice(productDto.getPrice());
    return productMapper.toDto(productRepository.save(product));
  }

  @Transactional
  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}

