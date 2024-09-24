package com.cp.kku.housely.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp.kku.housely.model.Category;
import com.cp.kku.housely.model.Product;
import com.cp.kku.housely.service.CategoryService;
import com.cp.kku.housely.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategoryId(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }
    
    @GetMapping("/room/{roomId}")
    public List<Product> getProductsByRoomId(@PathVariable Long roomId) {
        return productService.getProductsByRoomId(roomId);
    }

    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product) {
        List<Category> categories = new ArrayList<>();
        for (Category category : product.getCategories()) {
            categories.add(categoryService.getCategoryById(category.getCategoryId()).orElseThrow());
        }
        product.setCategories(categories);
        return productService.createProduct(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updateProduct) {
        return productService.updateProduct(id, updateProduct)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
}
