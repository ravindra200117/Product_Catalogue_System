package com.productcatalogue.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.productcatalogue.model.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>
{
	public Product findById(int id);
	
	List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoriesContainingIgnoreCase(String category);

	Page<Product> findAll(Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findByCategoriesContainingIgnoreCase(String category, Pageable pageable);

	
}
