package com.example.japring_mysql_timezone_test.repostiory;

import com.example.japring_mysql_timezone_test.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
