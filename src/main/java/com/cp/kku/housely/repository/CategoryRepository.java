package com.cp.kku.housely.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cp.kku.housely.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
