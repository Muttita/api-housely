package com.cp.kku.housely.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cp.kku.housely.service.CategoryService;

@Configuration
public class CategoryConfig {
	@Bean
	public CategoryService categoryService(CategoryRepository categoryRepository) {
		return new CategoryService(categoryRepository);
	}
}
