package com.prowings.project_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prowings.project_management.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByCatagoryAndPrice(String catagory, double price);
	
	public List<Product> findByCatagoryOrPrice(String catagory, double price);

	public List<Product> findByNameStartingWith(String startingWith);

	public Integer countByCatagory(String catagory);

	public boolean existsByCatagory(String catagory);

	public List<Product> findByPriceBetween(double minPrice, double maxPrice);
	

}
