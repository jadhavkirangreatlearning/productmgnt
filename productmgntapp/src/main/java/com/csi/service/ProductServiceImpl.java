package com.csi.service;

import com.csi.model.Product;
import com.csi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {

    @Autowired
    ProductRepo productRepoImpl;

    public Product saveData(Product product) {
        return productRepoImpl.save(product);
    }

    public List<Product> getAllData() {
        return productRepoImpl.findAll();
    }

    public Optional<Product> getDataById(int productId) {
        return productRepoImpl.findById(productId);
    }

    public List<Product> getDataByName(String productName) {
        return getAllData().stream().filter(product -> product.getProductName().equals(productName)).collect(Collectors.toList());
    }

    public List<Product> sortByName() {
        return getAllData().stream().sorted(Comparator.comparing(Product::getProductName)).collect(Collectors.toList());
    }

    public List<Product> sortByPrice(){
        return getAllData().stream().sorted(Comparator.comparingDouble(Product::getProductPrice)).collect(Collectors.toList());
    }

    public Product updateData(Product product){
        return productRepoImpl.save(product);
    }

    public void deleteDataById(int productId){
        productRepoImpl.deleteById(productId);
    }

    public void deleteAllData(){
        productRepoImpl.deleteAll();
    }
}
