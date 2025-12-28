package com.emsi.ecommerce.service;

import com.emsi.ecommerce.entity.ProductEntity;
import java.util.List;

public interface ProductService {
    ProductEntity create(ProductEntity p);
    ProductEntity update(Long id, ProductEntity p);
    void delete(Long id);
    ProductEntity get(Long id);
    List<ProductEntity> list();
    List<ProductEntity> listByCategory(Long categoryId);
}
