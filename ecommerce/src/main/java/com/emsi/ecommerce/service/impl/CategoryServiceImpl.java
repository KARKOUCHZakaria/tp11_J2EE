package com.emsi.ecommerce.service.impl;

import com.emsi.ecommerce.entity.CategoryEntity;
import com.emsi.ecommerce.repository.CategoryRepository;
import com.emsi.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo;

    public CategoryServiceImpl(CategoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public CategoryEntity create(CategoryEntity c) {
        return repo.save(c);
    }

    @Override
    public CategoryEntity update(Long id, CategoryEntity c) {
        CategoryEntity existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setName(c.getName());
        existing.setDescription(c.getDescription());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public CategoryEntity get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<CategoryEntity> list() {
        return repo.findAll();
    }
}
