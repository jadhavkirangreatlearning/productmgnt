package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Product;
import com.csi.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @PostMapping("/savedata")
    public ResponseEntity<Product> saveData(@Valid @RequestBody Product product) {
        log.info("#####Trying to save data for Product: " + product.getProductName());
        return new ResponseEntity<>(productServiceImpl.saveData(product), HttpStatus.CREATED);
    }

    @GetMapping("/getdatabyid/{productId}")
    public ResponseEntity<Optional<Product>> getDataById(@PathVariable int productId) {
        return ResponseEntity.ok(productServiceImpl.getDataById(productId));
    }

    @GetMapping("/getdatabyname/{productName}")
    public ResponseEntity<List<Product>> getDataByName(@PathVariable String productName) {
        return ResponseEntity.ok(productServiceImpl.getDataByName(productName));
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<?> sortByName(){
        return ResponseEntity.ok(productServiceImpl.sortByName());
    }
    @GetMapping("/sortbyprice")
    public ResponseEntity<?> sortByPrice(){
        return ResponseEntity.ok(productServiceImpl.sortByPrice());
    }


    @GetMapping("/getalldata")
    public ResponseEntity<List<Product>> getAllData() {
        return ResponseEntity.ok(productServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{productId}")
    public ResponseEntity<Product> updateData(@PathVariable int productId, @Valid @RequestBody Product product) {
        //
        Product product1 = productServiceImpl.getDataById(productId).orElseThrow(() -> new RecordNotFoundException("Product Id Does Not Exist"));

        product1.setProductPrice(product.getProductPrice());
        product1.setProductCode(product.getProductCode());
        product1.setProductLaunchDate(product.getProductLaunchDate());
        product1.setProductName(product.getProductName());

        return new ResponseEntity<>(productServiceImpl.updateData(product1), HttpStatus.CREATED);


    }


    @DeleteMapping("/deletedatabyid/{productId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int productId) {
        productServiceImpl.deleteDataById(productId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {
        productServiceImpl.deleteAllData();
        return ResponseEntity.ok("All Data Deleted Successfully");
    }
}
