package com.example.ProducRegistrationCRUD.controller;

import com.example.ProducRegistrationCRUD.model.Product;
import com.example.ProducRegistrationCRUD.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getProduct() {
        try {
            List<Product> productList = new ArrayList<>();
            productRepository.findAll().forEach(productList::add);

            if (productList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(productList, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> productData = productRepository.findById(id);

        return productData.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product productObj = productRepository.save(product);
        return new ResponseEntity<>(productObj, HttpStatus.OK);
    }

    @PostMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody Product newProductData) {
        Optional<Product> oldProductData = productRepository.findById(id);

        if (oldProductData.isPresent()) {
            Product updateProductData = oldProductData.get();

            updateProductData.setQuantity(newProductData.getQuantity());
            updateProductData.setProduct_value(newProductData.getProduct_value());

            Product productObj = productRepository.save(updateProductData);
            return new ResponseEntity<>(productObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<HttpStatus> removeProductById(@PathVariable Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
