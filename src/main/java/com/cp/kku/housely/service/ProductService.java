package com.cp.kku.housely.service;

import org.springframework.stereotype.Service;

import com.cp.kku.housely.model.Product;
import com.cp.kku.housely.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setProductCode(productDetails.getProductCode());
            product.setBrandName(productDetails.getBrandName());
            product.setProductName(productDetails.getProductName());
            product.setPrice(productDetails.getPrice());
            product.setQuantity(productDetails.getQuantity());
            product.setImageBase64(productDetails.getImageBase64());
            product.setDescription(productDetails.getDescription());
            product.setCategories(productDetails.getCategories());
            product.setRooms(productDetails.getRooms());
            return productRepository.save(product);
        });
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    
    public List<Product> getProductsByRoomId(Long roomId) {
        return productRepository.findByRoomId(roomId);
    }
    
}
