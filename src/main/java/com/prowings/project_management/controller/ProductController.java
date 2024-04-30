package com.prowings.project_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.project_management.entity.Product;
import com.prowings.project_management.service.ProductService;




@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable int id) {
		return productService.getProductById(id);
	}

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@PostMapping("/products")
	public String saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable int id) {

		productService.deleteProductById(id);

	}
	
	@GetMapping("/products/catagory")
	public List<Product> getAllProductsWithCatagoryAndPrice(@RequestParam String catagory, @RequestParam double price){
		
		return productService.getAllProductsWithCatagoryAndPrice(catagory, price);
		
	}
	
	@GetMapping("/products/cat")
	public List<Product> getAllProductsWithCatagoryOrPrice(@RequestParam String catagory, @RequestParam double price){
		
		return productService.getAllProductsWithCatagoryOrPrice(catagory, price);
		
	}
	
	@GetMapping("/products/startingwith/{startingWith}")
	public List<Product> getAllProductsWithCatagoryAndPrice(@PathVariable String startingWith){
		
		return productService.getAllProductsNameStartingWith(startingWith);
	}
	
	@GetMapping("/products/catagory/count/{catagory}")
	public Integer getProductCountByCatagory(@PathVariable String catagory) {
		
		return productService.getProductCountByCatagory(catagory);
	}
	
	@GetMapping("/products/catagory/exist/{catagory}")
	public boolean getProductExistCatagory(@PathVariable String catagory) {
		
		return productService.getProductExistCatagory(catagory);
		
	}
	
	@GetMapping("/products/withinPriceRange")
	public List<Product> getAllProductsWithinPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice)
	{
		return productService.getAllProductsWithinPriceRange(minPrice, maxPrice);
	}
	
	
	
}