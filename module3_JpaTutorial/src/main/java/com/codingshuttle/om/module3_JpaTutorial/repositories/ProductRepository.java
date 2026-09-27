package com.codingshuttle.om.module3_JpaTutorial.repositories;

import com.codingshuttle.om.module3_JpaTutorial.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    //THE IMPLEMENTATION OF THE BELOW METHODS IS HANDLED BY HIBERNATE
    ProductEntity findByTitle(String pepsi);
    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);
    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int quantity, BigDecimal price);
    List<ProductEntity> findByTitleLike(String title);

    //THE IMPLEMENTATION OF THIS METHOD IS DEFINED BY US USING JPQL
    @Query("select e from ProductEntity e where e.title =: title and e.price =: price") //THIS IS THE DESC
    ProductEntity findByTitleAndPrice(String title, BigDecimal price);
}
