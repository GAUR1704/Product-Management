package com.prowings.project_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prowings.project_management.dao.ProductRepository;
import com.prowings.project_management.entity.Product;
import com.prowings.project_management.exception.InvalidProductDetailsException;
import com.prowings.project_management.exception.ProductDeletionFailedException;
import com.prowings.project_management.exception.ProductNotFoundException;
import com.prowings.project_management.exception.ProductUpdateFailedException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(long id) {
    	System.out.println("inside impl");
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new ProductNotFoundException("Product of specified ID is not present in System!!");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public String saveProduct(Product product) {
        if (validProduct(product)) {
            productRepository.save(product);
            return "Product saved successfully";
        } else {
            throw new InvalidProductDetailsException("Product details are not valid!!");
        }
    }

    private boolean validProduct(Product product) {
        if (product.getName().length() < 5) {
            throw new InvalidProductDetailsException("Product Name is not valid!!");
        } else if (product.getPrice() < 100) {
            throw new InvalidProductDetailsException("Product price must be greater than 100");
        } else {
            return true;
        }
    }

    @Override
    public Product updateProduct(long id, Product product) {
    	
        Product existingProductOptional = productRepository.findById(id).orElse(null);
        
        if (existingProductOptional != null) {
        	
        	existingProductOptional.setName(product.getName());
        	existingProductOptional.setPrice(product.getPrice());
        	existingProductOptional.setCatagory(product.getCatagory());
            
            return productRepository.save(existingProductOptional);
            
        } else 
            throw new ProductUpdateFailedException("Product with id " + id + " not found in database, So updation can't be done.");
        
    }

    @Override
    public void deleteProductById(long id) {
    	
        if (!productRepository.existsById(id))
        	
            throw new ProductDeletionFailedException("Product with id " + id + " not found in database.");
        
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProductsWithCatagoryAndPrice(String catagory, double price) {
        return productRepository.findByCatagoryAndPrice(catagory, price);
    }

    @Override
    public List<Product> getAllProductsWithCatagoryOrPrice(String catagory, double price) {
        return productRepository.findByCatagoryOrPrice(catagory, price);
    }

    @Override
    public List<Product> getAllProductsNameStartingWith(String startingWith) {
        return productRepository.findByNameStartingWith(startingWith);
    }

    @Override
    public Integer getProductCountByCatagory(String catagory) {
        return productRepository.countByCatagory(catagory);
    }

    @Override
    public boolean getProductExistCatagory(String catagory) {
        return productRepository.existsByCatagory(catagory);
    }

    @Override
    public List<Product> getAllProductsWithinPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}