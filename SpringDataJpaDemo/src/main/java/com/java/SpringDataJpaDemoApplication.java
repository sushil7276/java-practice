package com.java;

import com.java.models.Product;
import com.java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringDataJpaDemoApplication implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product p = new Product();
        p.setName("Red Shev");
        p.setDescription("This is snack product eat in upwas");
        p.setPrice(BigDecimal.valueOf(40.00));
        p.setStock(30);

//        productService.save(p);
//        productService.update(2,p);
        Product pDb = productService.findById(1);
        System.out.println(pDb.getName()+" | "+pDb.getPrice());
    }
}
