package com.prowings.project_management.service;

import java.util.List;

import com.prowings.project_management.entity.Product;

public interface ProductService {
	
	public Product getProductById(long id);

	public List<Product> getAllProducts();

	public String saveProduct(Product product);

	public Product updateProduct(Product product);
	
	public void deleteProductById(long id);

}
