package com.coursework.Kotik.Repositories;

import com.coursework.Kotik.Models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByType (String type);

}
