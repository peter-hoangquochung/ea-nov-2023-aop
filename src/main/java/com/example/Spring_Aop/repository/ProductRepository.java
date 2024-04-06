package com.example.Spring_Aop.repository;

import com.example.Spring_Aop.entity.*;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {
}
