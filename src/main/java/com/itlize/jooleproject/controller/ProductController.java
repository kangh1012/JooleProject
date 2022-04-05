package com.itlize.jooleproject.controller;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.entity.Project;
import com.itlize.jooleproject.entity.ProductToProject;
import com.itlize.jooleproject.service.ProductService;
import com.itlize.jooleproject.service.ProductToProjectService;
import com.itlize.jooleproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProductToProjectService productToProjectService;

    /***
     * Product CRUD
     * **/

    @PostMapping("/createProduct")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> createProduct(){
        Product product = productService.createNewProduct();
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/fetchAllProduct")
    public ResponseEntity<?> fetchAllProduct() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/fetchProductById")
    public ResponseEntity<?> fetchProductById(@RequestParam(name = "productId") Long id){
        Product findProduct = productService.findById(id);

        if (findProduct == null){
            return new ResponseEntity<>("{\"message: \" System do not have this product yet. }", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(findProduct, HttpStatus.OK);
    }

    @GetMapping("/fetchProductByType")
    public ResponseEntity<?> fetchProductByType(@RequestParam(name = "productType") String type) {
        List<Product> findProducts = productService.findByType(type);

        if (findProducts == null) {
            return new ResponseEntity<>(new ArrayList<Product>(), HttpStatus.OK);
        }

        return new ResponseEntity<>(findProducts, HttpStatus.OK);
    }

    @GetMapping("/fetchProductByCategory")
    public ResponseEntity<?> fetchProductByCategory(@RequestParam(name = "category") String category) {
        List<Product> findProducts = productService.findByCategory(category);

        if (findProducts == null) {
            return new ResponseEntity<>(new ArrayList<Product>(), HttpStatus.OK);
        }

        return new ResponseEntity<>(findProducts, HttpStatus.OK);
    }

    @GetMapping("categoryAndType")
    public ResponseEntity<?> findCategoryAndType() {
        Map<String, List<String>> map = productService.findCategoryAndType();

        if(map == null) {
            return new ResponseEntity<>("{\"message: \" No results retrieved.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //give default to false so it's not asking for them
    @PutMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product newProduct){
        Product findProduct = productService.findById(newProduct.getProductId());

        if (findProduct == null){
            return new ResponseEntity<>("{\"message: \" System do not have this product yet. }", HttpStatus.BAD_REQUEST);
        }
        findProduct.setType(newProduct.getType());
        findProduct.setManufacturer(newProduct.getManufacturer());
        findProduct.setSeries(newProduct.getSeries());
        findProduct.setModel(newProduct.getModel());
        findProduct.setModelYear(newProduct.getModelYear());
        findProduct.setAirFlow(newProduct.getAirFlow());
        findProduct.setMaxPower(newProduct.getMaxPower());
        findProduct.setSound(newProduct.getSound());
        findProduct.setFanSweepDiameter(newProduct.getFanSweepDiameter());
        findProduct.setCategory(newProduct.getCategory());
        findProduct.setImagePath(newProduct.getImagePath());
        Product result = productService.save(findProduct);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/deleteProductById")
    public ResponseEntity<?> deleteProductById(@RequestParam(name = "productId")Long id){
        Product findProduct = productService.findById(id);

        if (findProduct == null){
            return new ResponseEntity<>("{\"message: \" System do not have this product yet. }", HttpStatus.BAD_REQUEST);
        }
        productService.delete(findProduct);
        return new ResponseEntity<>("{\"message: \" Deleted target product. }", HttpStatus.OK);
    }

    /**
     * ProductToProject CRUD
     * **/

    @PostMapping("/addProductToProject")
    public ResponseEntity<?> addProductToProject(
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "projectId") Long projectId){

        Product findProduct = productService.findById(productId);

        if (findProduct == null){
            return new ResponseEntity<>("{\"message: \" System do not have this product yet. }", HttpStatus.BAD_REQUEST);
        }
        Project findProject = projectService.findById(projectId);

        if (findProject == null){
            return new ResponseEntity<>("{\"message: \" System do not have this project yet. }", HttpStatus.BAD_REQUEST);
        }

        ProductToProject ptp = productToProjectService.findByProductAndProject(findProduct, findProject);
        if (ptp != null){
            return new ResponseEntity<>("{\"message: \" The project already have this product. }", HttpStatus.CONFLICT);
        }

        ProductToProject pr = productToProjectService.addProductToProject(findProduct, findProject);


        return new ResponseEntity<>(pr, HttpStatus.CREATED);
    }

    @PutMapping("/setPriceToRelationship")
    public ResponseEntity<?> setPriceToRelationship(@RequestParam(name = "productId") Long productId,
                                                    @RequestParam(name = "projectId") Long projectId,
                                                    @RequestParam(name= "price") Double price){
        Product findProduct = productService.findById(productId);

        if (findProduct == null){
            return new ResponseEntity<>("{\"message: \" System do not have this product yet. }", HttpStatus.BAD_REQUEST);
        }
        Project findProject = projectService.findById(projectId);

        if (findProject == null){
            return new ResponseEntity<>("{\"message: \" System do not have this project yet. }", HttpStatus.BAD_REQUEST);
        }

        ProductToProject find = productToProjectService.findByProductAndProject(findProduct, findProject);

        if (find == null){
            return new ResponseEntity<>("{\"message: \" System do not have this relationship yet. }", HttpStatus.BAD_REQUEST);
        }

        productToProjectService.setPriceQuote(find, price);
        return new ResponseEntity<>(price, HttpStatus.ACCEPTED);
    }

    @GetMapping("/fetchProductProjectRelationship")
    public ResponseEntity<?> fetchProductProjectRelationship( @RequestParam(name = "productId") Long productId,
                                                              @RequestParam(name = "projectId") Long projectId){
        Product findProduct = productService.findById(productId);

        if (findProduct == null){
            return new ResponseEntity<>("{\"message: \" System do not have this product yet. }", HttpStatus.BAD_REQUEST);
        }
        Project findProject = projectService.findById(projectId);

        if (findProject == null){
            return new ResponseEntity<>("{\"message: \" System do not have this project yet. }", HttpStatus.BAD_REQUEST);
        }

        ProductToProject pr = productToProjectService.findByProductAndProject(findProduct, findProject);

        if (pr == null){
            return new ResponseEntity<>("{\"message: \" System do not have this relationship yet. }", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(pr, HttpStatus.FOUND);
    }

    @GetMapping("/deleteRelationshipByIds")
    public ResponseEntity<?> deleteRelationship(@RequestParam(name = "productId") Long productId,
                                                @RequestParam(name = "projectId") Long projectId){
        Product findProduct = productService.findById(productId);

        if (findProduct == null){
            return new ResponseEntity<>("{\"message: \" System do not have this product yet. }", HttpStatus.BAD_REQUEST);
        }
        Project findProject = projectService.findById(projectId);

        if (findProject == null){
            return new ResponseEntity<>("{\"message: \" System do not have this project yet. }", HttpStatus.BAD_REQUEST);
        }
        ProductToProject pr = productToProjectService.findByProductAndProject(findProduct, findProject);

        if (pr == null){
            return new ResponseEntity<>("{\"message: \" System do not have this relationship yet. }", HttpStatus.BAD_REQUEST);
        }

        productToProjectService.delete(pr);

        return new ResponseEntity<>("{\"message: \" deleted target relationship }", HttpStatus.OK);
    }




}
