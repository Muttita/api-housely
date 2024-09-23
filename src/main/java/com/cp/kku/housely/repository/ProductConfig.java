package com.cp.kku.housely.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cp.kku.housely.service.ProductService;

@Configuration
public class ProductConfig {

    @Bean
	public ProductService productService(ProductRepository productRepository) {
		return new ProductService(productRepository);
	}
}