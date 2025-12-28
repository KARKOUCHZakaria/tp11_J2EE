package com.emsi.ecommerce.config;

import com.emsi.ecommerce.entity.CategoryEntity;
import com.emsi.ecommerce.entity.ProductEntity;
import com.emsi.ecommerce.repository.CategoryRepository;
import com.emsi.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
            // Check if data already exists
            if (categoryRepository.count() == 0) {
                // Create categories
                CategoryEntity electronics = new CategoryEntity(null, "Electronics", "Electronic devices and gadgets");
                CategoryEntity clothing = new CategoryEntity(null, "Clothing", "Fashion and apparel");
                CategoryEntity books = new CategoryEntity(null, "Books", "Books and educational materials");
                CategoryEntity home = new CategoryEntity(null, "Home & Garden", "Home decoration and garden supplies");
                CategoryEntity sports = new CategoryEntity(null, "Sports", "Sports equipment and accessories");

                categoryRepository.save(electronics);
                categoryRepository.save(clothing);
                categoryRepository.save(books);
                categoryRepository.save(home);
                categoryRepository.save(sports);

                System.out.println("✅ Categories initialized successfully!");

                // Create products
                productRepository.save(new ProductEntity(null, "Laptop Dell XPS 13",
                        "High-performance ultrabook with Intel Core i7", 1299.99, 15, electronics));
                productRepository.save(new ProductEntity(null, "iPhone 15 Pro", "Latest Apple smartphone with A17 chip",
                        999.99, 25, electronics));
                productRepository.save(new ProductEntity(null, "Samsung Galaxy S24",
                        "Premium Android smartphone with AI features", 899.99, 30, electronics));
                productRepository.save(new ProductEntity(null, "Sony Headphones WH-1000XM5",
                        "Premium noise-cancelling wireless headphones", 349.99, 40, electronics));
                productRepository.save(
                        new ProductEntity(null, "iPad Air", "Powerful tablet with M1 chip", 599.99, 20, electronics));

                productRepository
                        .save(new ProductEntity(null, "Men's Jeans", "Classic fit denim jeans", 59.99, 100, clothing));
                productRepository
                        .save(new ProductEntity(null, "Women's Dress", "Elegant summer dress", 79.99, 50, clothing));
                productRepository.save(new ProductEntity(null, "T-Shirt Pack (3)", "Comfortable cotton t-shirts", 29.99,
                        150, clothing));
                productRepository.save(new ProductEntity(null, "Winter Jacket", "Warm waterproof winter jacket", 139.99,
                        35, clothing));
                productRepository.save(
                        new ProductEntity(null, "Running Shoes", "Lightweight running shoes", 89.99, 60, clothing));

                productRepository.save(new ProductEntity(null, "The Great Gatsby",
                        "Classic American novel by F. Scott Fitzgerald", 14.99, 80, books));
                productRepository.save(new ProductEntity(null, "1984 by George Orwell",
                        "Dystopian social science fiction novel", 12.99, 90, books));
                productRepository.save(new ProductEntity(null, "Java Programming Guide",
                        "Complete guide to Java development", 49.99, 45, books));
                productRepository.save(new ProductEntity(null, "Clean Code",
                        "A Handbook of Agile Software Craftsmanship", 39.99, 55, books));
                productRepository.save(new ProductEntity(null, "Harry Potter Collection", "Complete set of 7 books",
                        99.99, 25, books));

                productRepository.save(new ProductEntity(null, "Coffee Maker", "Programmable coffee maker with timer",
                        79.99, 40, home));
                productRepository.save(
                        new ProductEntity(null, "Blender Pro", "High-power blender for smoothies", 129.99, 30, home));
                productRepository.save(new ProductEntity(null, "Garden Tool Set",
                        "Complete gardening toolkit (10 pieces)", 89.99, 20, home));
                productRepository.save(new ProductEntity(null, "Indoor Plant Collection",
                        "Set of 3 air-purifying plants", 49.99, 50, home));
                productRepository
                        .save(new ProductEntity(null, "LED Lamp", "Energy-efficient desk lamp", 34.99, 70, home));

                productRepository
                        .save(new ProductEntity(null, "Yoga Mat", "Non-slip exercise yoga mat", 29.99, 100, sports));
                productRepository.save(new ProductEntity(null, "Dumbbell Set", "Adjustable dumbbell set (5-25kg)",
                        149.99, 25, sports));
                productRepository.save(new ProductEntity(null, "Tennis Racket", "Professional tennis racket with cover",
                        119.99, 15, sports));
                productRepository
                        .save(new ProductEntity(null, "Basketball", "Official size basketball", 24.99, 60, sports));
                productRepository.save(new ProductEntity(null, "Bicycle Helmet", "Safety-certified cycling helmet",
                        49.99, 45, sports));

                System.out.println("✅ Products initialized successfully!");
                System.out.println("📊 Total categories: " + categoryRepository.count());
                System.out.println("📊 Total products: " + productRepository.count());
            } else {
                System.out.println("ℹ️ Database already initialized. Skipping data initialization.");
            }
        };
    }
}
