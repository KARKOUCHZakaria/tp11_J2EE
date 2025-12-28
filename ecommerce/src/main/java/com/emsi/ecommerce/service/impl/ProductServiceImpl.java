package com.emsi.ecommerce.service.impl;

import com.emsi.ecommerce.entity.ProductEntity;
import com.emsi.ecommerce.entity.CategoryEntity;
import com.emsi.ecommerce.repository.ProductRepository;
import com.emsi.ecommerce.repository.CategoryRepository;
import com.emsi.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public ProductServiceImpl(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ProductEntity create(ProductEntity p) {
        if (p.getCategory() != null && p.getCategory().getId() != null) {
            CategoryEntity cat = categoryRepo.findById(p.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            p.setCategory(cat);
        } else {
            p.setCategory(null);
        }
        return productRepo.save(p);
    }

    @Override
    public ProductEntity update(Long id, ProductEntity p) {
        ProductEntity existing = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(p.getName());
        existing.setPrice(p.getPrice());
        existing.setStock(p.getStock());
        if (p.getCategory() != null && p.getCategory().getId() != null) {
            CategoryEntity cat = categoryRepo.findById(p.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existing.setCategory(cat);
        } else {
            existing.setCategory(null);
        }
        return productRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public ProductEntity get(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductEntity> list() {
        return productRepo.findAll();
    }

    @Override
    public List<ProductEntity> listByCategory(Long categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }
}
