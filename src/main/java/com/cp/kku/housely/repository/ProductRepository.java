package com.cp.kku.housely.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cp.kku.housely.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    // Additional query methods can be defined here if needed
	
	@Query("SELECT p FROM Product p JOIN p.categories c WHERE c.categoryId = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);
	
	@Query("SELECT p FROM Product p JOIN p.rooms r WHERE r.id = :roomId")
    List<Product> findByRoomId(@Param("roomId") Long roomId);

	
}
