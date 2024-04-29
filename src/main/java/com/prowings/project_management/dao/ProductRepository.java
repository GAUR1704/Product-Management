package com.prowings.project_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prowings.project_management.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
