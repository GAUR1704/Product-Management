package com.prowings.project_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prowings.project_management.dao.ProductRepository;
import com.prowings.project_management.entity.Product;
import com.prowings.project_management.exception.InvalidProductDetailsException;
import com.prowings.project_management.exception.ProductNotFoundException;


@Service
public class  ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository; 


	@Override
	public Product getProductById(long id) {

		Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else
            throw new ProductNotFoundException("Product of specified ID is not present in System!!");

	}

	@Override
	public List<Product> getAllProducts() {

		return productRepository.findAll();
	}

	@Override
	public String saveProduct(Product product) {
		if (validProduct(product)) 
			
	        productRepository.save(product);
		
	        return "Product saved successfully";
	     

	}

	private boolean validProduct(Product product) {

		if(product.getName().length() < 5)
			throw new InvalidProductDetailsException("Product Name is not valid!!");
		else if(product.getPrice()<100)
			throw new InvalidProductDetailsException("Product price must be greater than 100");
		else 
			return true;


	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProductById(long id){
        if (!productRepository.existsById(id)) {
        	
            throw new ProductNotFoundException("Product with id " + id + " not found in database.");
        }
        productRepository.deleteById(id);
    }

}