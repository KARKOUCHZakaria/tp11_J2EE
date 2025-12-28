package com.emsi.ecommerce.controller;

import com.emsi.ecommerce.entity.CategoryEntity;
import com.emsi.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryEntity> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public CategoryEntity get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> create(@Valid @RequestBody CategoryEntity c) {
        CategoryEntity created = service.create(c);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public CategoryEntity update(@PathVariable Long id, @Valid @RequestBody CategoryEntity c) {
        return service.update(id, c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
