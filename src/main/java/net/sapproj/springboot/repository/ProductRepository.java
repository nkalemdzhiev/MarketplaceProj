package net.sapproj.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.sapproj.springboot.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    
    List<Product> findByName(String name);
    
}
