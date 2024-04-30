package com.prowings.project_management.service;

import java.util.List;

import com.prowings.project_management.entity.Product;

public interface ProductService {
	
	public Product getProductById(long id);

	public List<Product> getAllProducts();

	public String saveProduct(Product product);

	public Product updateProduct(Product product);
	
	public void deleteProductById(long id);

	public List<Product> getAllProductsWithCatagoryAndPrice(String catagory, double price);
	
	public List<Product> getAllProductsWithCatagoryOrPrice(String catagory, double price);

	public List<Product> getAllProductsNameStartingWith(String startingWith);

	public Integer getProductCountByCatagory(String catagory);

	public boolean getProductExistCatagory(String catagory);

	public List<Product> getAllProductsWithinPriceRange(double minPrice, double maxPrice);



}
