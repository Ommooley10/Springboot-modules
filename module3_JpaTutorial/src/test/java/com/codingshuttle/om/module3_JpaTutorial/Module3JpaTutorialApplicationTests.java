package com.codingshuttle.om.module3_JpaTutorial;

import com.codingshuttle.om.module3_JpaTutorial.entities.ProductEntity;
import com.codingshuttle.om.module3_JpaTutorial.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class Module3JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository(){
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle1234")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(123.45))
				.quantity(12)
				.build();

		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	void getRepository(){
		List<ProductEntity> entities = productRepository.findAll();
		System.out.println(entities);
	}

	@Test
	void getRepository2(){
		ProductEntity productEntity = productRepository.findByTitle("Pepsi");
		//HERE WE CREATED A CUSTOM METHOD "FindByTitle" (implementation is handled by hibernate automatically)
		//We just need to declare the method name in "ProductRepository", and the rest is taken care by hibernate
	}

	@Test
	void getRepository3(){
		List<ProductEntity> productEntities = productRepository.findByCreatedAtAfter(LocalDateTime.of(2024, 1, 1, 0, 0, 0));
		System.out.println(productEntities);
	}
}
