package com.itlize.jooleproject.controller;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.service.ProductService;
import com.itlize.jooleproject.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/add")
    public JsonResult<Void> add(Product product){
        LocalDateTime date = LocalDateTime.now();
        product.setTimeCreated(date);
        product.setLastModified(date);

        JsonResult<Void> result = new JsonResult<>();
        try{
            productService.save(product);
            result.setState(200);
            result.setMessage("Successfully added product.");
        }catch(Exception e){
            result.setState(4000);
            result.setMessage("Error happens when trying to add product.");
        }

        return result;
    }

}
