package com.prowings.project_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.project_management.entity.Product;
import com.prowings.project_management.exception.ProductUpdateFailedException;
import com.prowings.project_management.service.ProductService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        log.info("Received request to get product with ID: {}", id);
        Product product = productService.getProductById(id);
        if (product != null) {
            log.info("Product found with ID: {}", id);
            return ResponseEntity.ok(product);
        } else {
            log.warn("Product not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("Received request to get all products");
        List<Product> products = productService.getAllProducts();
        log.info("Found {} products", products.size());
        return ResponseEntity.ok(products);
    }

    @PostMapping("/products")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        log.info("Received request to save product: {}", product);
        String savedProductId = productService.saveProduct(product);
        log.info("Product saved successfully with ID: {}", savedProductId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductId);
    }
    
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        log.info("Received request to update product with ID: {}", id);
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            log.info("Product with ID {} updated successfully", id);
            return ResponseEntity.ok(updatedProduct);
        } else {
            log.error("Failed to update product with ID: {}", id);
            throw new ProductUpdateFailedException("Product with id " + id + " not found in database, So updation can't be done.");
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        log.info("Received request to delete product with ID: {}", id);
        productService.deleteProductById(id);
        log.info("Product with ID {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/catagory")
    public ResponseEntity<List<Product>> getAllProductsWithCatagoryAndPrice(@RequestParam @NonNull String catagory, @RequestParam double price) {
        log.info("Received request to get products with category: {} and price: {}", catagory, price);
        List<Product> products = productService.getAllProductsWithCatagoryAndPrice(catagory, price);
        log.info("Found {} products matching the criteria", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/cat")
    public ResponseEntity<List<Product>> getAllProductsWithCatagoryOrPrice(@RequestParam @NonNull String catagory, @RequestParam double price) {
        log.info("Received request to get products with category: {} or price: {}", catagory, price);
        List<Product> products = productService.getAllProductsWithCatagoryOrPrice(catagory, price);
        log.info("Found {} products matching the criteria", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/startingwith/{startingWith}")
    public ResponseEntity<List<Product>> getAllProductsWithCatagoryAndPrice(@PathVariable @NonNull String startingWith) {
        log.info("Received request to get products with name starting with: {}", startingWith);
        List<Product> products = productService.getAllProductsNameStartingWith(startingWith);
        log.info("Found {} products with names starting with: {}", products.size(), startingWith);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/catagory/count/{catagory}")
    public ResponseEntity<Integer> getProductCountByCatagory(@PathVariable @NonNull String catagory) {
        log.info("Received request to get product count for category: {}", catagory);
        Integer count = productService.getProductCountByCatagory(catagory);
        log.info("Found {} products for category: {}", count, catagory);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/products/catagory/exist/{catagory}")
    public ResponseEntity<Boolean> getProductExistCatagory(@PathVariable @NonNull String catagory) {
        log.info("Received request to check if products exist for category: {}", catagory);
        boolean exists = productService.getProductExistCatagory(catagory);
        log.info("Products exist for category {}: {}", catagory, exists);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/products/withinPriceRange")
    public ResponseEntity<List<Product>> getAllProductsWithinPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        log.info("Received request to get products within price range: {} - {}", minPrice, maxPrice);
        List<Product> products = productService.getAllProductsWithinPriceRange(minPrice, maxPrice);
        log.info("Found {} products within price range: {} - {}", products.size(), minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
    
//    @GetMapping("/products/page")
//    public ResponseEntity<Page<Product>> getProductsPagination(@RequestParam(defaultValue = "0") int page,
//                                                                @RequestParam(defaultValue = "3") int size) {
//        PageRequest pageable = PageRequest.of(page, size);
//        Page<Product> productPage = productService.getAllProductsPagination(pageable);
//        return ResponseEntity.ok(productPage);
//    }

}