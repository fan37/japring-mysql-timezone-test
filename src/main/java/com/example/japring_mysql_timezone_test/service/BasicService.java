package com.example.japring_mysql_timezone_test.service;

import com.example.japring_mysql_timezone_test.entity.Product;
import com.example.japring_mysql_timezone_test.repostiory.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Basic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class BasicService {
    private ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(BasicService.class);


    public BasicService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @PostConstruct
    void script() throws JsonProcessingException {
        Product product = new Product();
        product.targetTime = OffsetDateTime.now();
//        product.targetTime = LocalDateTime.now();
        logger.info("[CHCH] targetTime: " + product.targetTime.toString());
        productRepository.save(product);

        Long savedId = product.id;

        productRepository.flush();

        Product savedProduct = productRepository.findById(savedId).orElseThrow();
        logger.info("[CHCH] saved targetTime: " + savedProduct.targetTime.toString());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        logger.info("[CHCH] saved product: " + mapper.writeValueAsString(savedProduct));

        System.out.println("Hi~! PostConstruct.");
    }
}
