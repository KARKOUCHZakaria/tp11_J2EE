package com.emsi.ecommerce.controller;

import com.emsi.ecommerce.entity.ProductEntity;
import com.emsi.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductEntity> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ProductEntity get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/category/{id}")
    public List<ProductEntity> listByCategory(@PathVariable Long id) {
        return service.listByCategory(id);
    }

    @PostMapping
    public ProductEntity create(@Valid @RequestBody ProductEntity p) {
        return service.create(p);
    }

    @PutMapping("/{id}")
    public ProductEntity update(@PathVariable Long id, @Valid @RequestBody ProductEntity p) {
        return service.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
