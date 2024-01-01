package com.example.ProducRegistrationCRUD.controller;

import com.example.ProducRegistrationCRUD.model.Product;
import com.example.ProducRegistrationCRUD.repository.ProductRepository;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping
    public ResponseEntity<List<Product>> getProduct(){

        try{
            List<Product> productList = new ArrayList<>();
            productRepository.findAll().forEach(productList::add);

            if(productList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping
    public void getProductById(){

    }
    @PostMapping
    public void addProduct(){

    }

    @PostMapping
    public void updateProductById(){

    }
    @DeleteMapping
    public void removeProductById(){

    }


}
