package com.itlize.jooleproject;

import com.itlize.jooleproject.entity.Product;
import com.itlize.jooleproject.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    public void saveTest() {
        Product original = new Product();
        original.setType("Fan");
        original.setManufacturer("Emerson");
        original.setModel("CF860");
        original.setModelYear(2015);
        original.setAirFlow(8000);

        Product saved = productService.save(original);

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getType()).isEqualTo(original.getType());
        Assertions.assertThat(saved.getManufacturer()).isEqualTo(original.getManufacturer());
        Assertions.assertThat(saved.getModel()).isEqualTo(original.getModel());
        Assertions.assertThat(saved.getModelYear()).isEqualTo(original.getModelYear());
        Assertions.assertThat(saved.getAirFlow()).isEqualTo(original.getAirFlow());
    }

    @Test
    public void findByIdTest() {
        Product expected = new Product();
        expected.setType("Fan");
        expected.setManufacturer("Emerson");
        expected.setModel("CF860");
        expected.setModelYear(2015);
        expected.setAirFlow(8000);

        Product actual = productService.findById(6L);

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getType()).isEqualTo(expected.getType());
        Assertions.assertThat(actual.getManufacturer()).isEqualTo(expected.getManufacturer());
        Assertions.assertThat(actual.getModel()).isEqualTo(expected.getModel());
        Assertions.assertThat(actual.getModelYear()).isEqualTo(expected.getModelYear());
        Assertions.assertThat(actual.getAirFlow()).isEqualTo(expected.getAirFlow());
    }

    @Test
    public void findAllTest() {
        Product product1 = new Product();
        product1.setType("Fan");
        product1.setManufacturer("Westinghouse");
        product1.setModel("78003");
        product1.setModelYear(2015);
        product1.setAirFlow(8500);

        Product product2 = new Product();
        product2.setType("Fan");
        product2.setManufacturer("Emerson");
        product2.setModel("CF860");
        product2.setModelYear(2017);
        product2.setAirFlow(8500);

        productService.save(product1);
        productService.save(product2);

        List<Product> products = productService.findAll();

        Assertions.assertThat(products.size()).isEqualTo(3);
    }

    @Test
    public void findByTypeTest() {
        List<Product> products = productService.findByType("Fan");

        for(Product product: products) {
            Assertions.assertThat(product.getType()).isEqualTo("Fan");
        }
    }

    @Test
    public void findByManufacturerTest() {
        List<Product> products = productService.findByManufacturer("Emerson");

        for(Product product: products) {
            Assertions.assertThat(product.getManufacturer()).isEqualTo("Emerson");
        }
    }

    @Test
    public void findByModelTest() {
        List<Product> products = productService.findByModel("78003");

        for(Product product: products) {
            Assertions.assertThat(product.getModel()).isEqualTo("78003");
        }
    }

    @Test
    public void findByModelYearBetweenTest() {
        List<Product> products = productService.findByModelYearBetween(2016, 2018);

        for(Product product: products) {
            Assertions.assertThat(product.getModelYear()).isGreaterThanOrEqualTo(2016);
            Assertions.assertThat(product.getModelYear()).isLessThanOrEqualTo(2018);
        }
    }

    @Test
    public void findByAirFlowBetweenTest() {
        List<Product> products = productService.findByAirFlowBetween(7500, 8000);

        for(Product product: products) {
            Assertions.assertThat(product.getAirFlow()).isGreaterThanOrEqualTo(7500);
            Assertions.assertThat(product.getAirFlow()).isLessThanOrEqualTo(8000);
        }
    }

    @Test
    public void deleteTest() {
        Product deletedProduct = productService.findById(12L);

        productService.delete(deletedProduct);

        deletedProduct = productService.findById(12L);
        Assertions.assertThat(deletedProduct).isNull();
    }
}
