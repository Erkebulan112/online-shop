package com.project_sql.online_shop.controllers;

import com.project_sql.online_shop.dtos.ProductDto;
import com.project_sql.online_shop.services.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public List<ProductDto> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public ProductDto getProductById(@PathVariable("id") Long id) {
    return productService.getProductById(id);
  }

  @PostMapping("/new-product")
  public ProductDto createProduct(@RequestBody ProductDto productDto) {
    return productService.addProduct(productDto);
  }

  @PatchMapping("/{id}/edit")
  public ProductDto updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
    productService.updateProduct(id, productDto);
    return productDto;
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable("id") Long id) {
    productService.deleteProduct(id);
  }
}
