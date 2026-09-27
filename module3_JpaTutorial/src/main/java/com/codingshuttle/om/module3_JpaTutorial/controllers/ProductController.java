package com.codingshuttle.om.module3_JpaTutorial.controllers;

import com.codingshuttle.om.module3_JpaTutorial.entities.ProductEntity;
import com.codingshuttle.om.module3_JpaTutorial.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    /*we should always use the standard MVC ARCHITECTURE or DTO PATTERN here but that would make the codebase
    unnecessary big, so for understanding sorting and pagination purposes here we are directly
    using Repository and Entity*/

    private final int PAGE_SIZE = 5;
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/sort")
    public List<ProductEntity> getAllProducts(@RequestParam(defaultValue = "id") String sortBy){

        //the below line says that two items have the same value for sortBy field then sort them on the basis of "price"
        //and if "price is also same then sort the mon the basis of "quantity"
        List<ProductEntity> entities = productRepository.findBy(Sort.by(
                Sort.Order.desc(sortBy),
                Sort.Order.asc("price"),
                Sort.Order.desc("quantity")
        ));

        return entities;

        /*HERE WE COULD HAVE ALSO WRITTEN THE ABOVE CODE LIKE THIS: (For Better Readability)
            Sort sort = Sort.by(
                Sort.Order.desc(sortBy),
                Sort.Order.asc("price"),
                Sort.Order.desc("quantity")
            );

            List<ProductEntity> entities = productRepository.findBy(sort);
            return entities;
          */
    }

    @GetMapping
    public Page<ProductEntity> getAllProductsByPage(@RequestParam(defaultValue = "id") String sortBy,
                                                    @RequestParam(defaultValue = "1") Integer pageNumber){

        Pageable pageable = PageRequest.of(pageNumber-1, PAGE_SIZE, Sort.by(Sort.Order.asc(sortBy)));
        return productRepository.findAll(pageable);

        /*HERE WE COULD HAVE ALSO WRITTEN THE ABOVE CODE LIKE THIS:
           return productRepository.findAll(PageRequest.of(pageNumber-1, PAGE_SIZE, Sort.by(Sort.Order.asc(sortBy))));
         */
    }


}
