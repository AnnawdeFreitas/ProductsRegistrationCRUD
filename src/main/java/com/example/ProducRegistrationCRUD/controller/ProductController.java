package com.example.ProducRegistrationCRUD.controller;

import com.example.ProducRegistrationCRUD.model.Product;
import com.example.ProducRegistrationCRUD.repository.ProductRepository;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getProduct(){

        try{
            List<Product> productList = new ArrayList<>();
            productRepository.findAll().forEach(productList::add);

            if(productList.isEmpty()){
                return new ResponseEntity<>(productList, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){

        Optional<Product> productData = productRepository.findById(id);

        if(productData.isPresent()){
            return new ResponseEntity<>(productData , HttpStatus.Ok);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/addProduct")
    public void addProduct(){

    }

    @PostMapping("/updateProduct")
    public void updateProductById(){

    }
    @DeleteMapping("/deleteProduct")
    public void removeProductById(){

    }


}
