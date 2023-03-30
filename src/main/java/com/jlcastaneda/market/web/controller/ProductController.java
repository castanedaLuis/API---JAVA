package com.jlcastaneda.market.web.controller;

import com.jlcastaneda.market.domain.Product;
import com.jlcastaneda.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all") //ResponseEntity no ayuda a manejar las repsuestas y llamados de nuestros controladores
    @ApiOperation("Get all superMarket products")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")  ///ResponseEntity no ayuda a manejar las repsuestas y llamados de nuestros controladores
    @ApiOperation("Serach at product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not Found"),
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product ", required = true, example = "7") @PathVariable("id") int productId) {
        return productService.getProduct(productId)
                .map(prod -> new ResponseEntity<>(prod, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}") //ResponseEntity no ayuda a manejar las repsuestas y llamados de nuestros controladores

    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save") //ResponseEntity no ayuda a manejar las repsuestas y llamados de nuestros controladores
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}") //ResponseEntity no ayuda a manejar las repsuestas y llamados de nuestros controladores
    public ResponseEntity delete(@PathVariable("id") int productId) {
        if(productService.delete((productId))){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
