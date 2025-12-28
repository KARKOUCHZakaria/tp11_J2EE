package com.emsi.ecommerce.service;

import com.emsi.ecommerce.entity.CategoryEntity;
import java.util.List;

public interface CategoryService {
    CategoryEntity create(CategoryEntity c);
    CategoryEntity update(Long id, CategoryEntity c);
    void delete(Long id);
    CategoryEntity get(Long id);
    List<CategoryEntity> list();
}