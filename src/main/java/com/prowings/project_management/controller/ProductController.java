package com.prowings.project_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.prowings.project_management.entity.Product;
import com.prowings.project_management.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/products")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        String savedProductId = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductId);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products/catagory")
    public ResponseEntity<List<Product>> getAllProductsWithCatagoryAndPrice(@RequestParam String catagory, @RequestParam double price) {
        List<Product> products = productService.getAllProductsWithCatagoryAndPrice(catagory, price);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/cat")
    public ResponseEntity<List<Product>> getAllProductsWithCatagoryOrPrice(@RequestParam String catagory, @RequestParam double price) {
        List<Product> products = productService.getAllProductsWithCatagoryOrPrice(catagory, price);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/startingwith/{startingWith}")
    public ResponseEntity<List<Product>> getAllProductsWithCatagoryAndPrice(@PathVariable String startingWith) {
        List<Product> products = productService.getAllProductsNameStartingWith(startingWith);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/catagory/count/{catagory}")
    public ResponseEntity<Integer> getProductCountByCatagory(@PathVariable String catagory) {
        Integer count = productService.getProductCountByCatagory(catagory);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/products/catagory/exist/{catagory}")
    public ResponseEntity<Boolean> getProductExistCatagory(@PathVariable String catagory) {
        boolean exists = productService.getProductExistCatagory(catagory);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/products/withinPriceRange")
    public ResponseEntity<List<Product>> getAllProductsWithinPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Product> products = productService.getAllProductsWithinPriceRange(minPrice, maxPrice);
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
