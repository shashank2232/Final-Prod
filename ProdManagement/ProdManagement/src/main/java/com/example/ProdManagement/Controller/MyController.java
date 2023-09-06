package com.example.ProdManagement.Controller;

import com.example.ProdManagement.Model.Request.ProductRequest;
import com.example.ProdManagement.Model.Response.ProductResponse;
import com.example.ProdManagement.Model.Response.ResponseClass;
import com.example.ProdManagement.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class MyController {

    @Autowired
    private ProductService productService;

    // sample API call
    @RequestMapping("/hello")
    public String hello(){
        return "hello there ";
    }
    @GetMapping("/callhello")
    private String getHello(){
        String uri = "http://localhost:8080/hello";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri,String.class);
        return result + "everyone";
    }

    // using 3rd party api call
    @GetMapping("/countries")
    public List<Object> getCountries(){
        String url = "https://restcountries.com/v3.1/all";
        RestTemplate restTemplate = new RestTemplate();
        Object[] countries = restTemplate.getForObject(url,Object[].class);
        return Arrays.asList(countries);
    }


    @PostMapping("/api/products")
    public ResponseEntity<ProductResponse> creatingProduct(@RequestBody ProductRequest productRequest){
        return productService.addingProduct(productRequest);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ResponseClass>> gettingAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<ResponseClass> gettingProductById(@PathVariable("id") Integer productCode){
        return productService.getProductById(productCode);
    }

    @GetMapping("/api/products/csvexport")
    public ResponseEntity<InputStreamResource> getFile() {
        String filename = "products.csv";
        InputStreamResource file = new InputStreamResource(productService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<String> updatingProduct(@PathVariable("id") Integer productCode, @RequestBody ProductRequest productRequest){
        return productService.updateProductData(productRequest,productCode);
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<ProductResponse> deletingProduct(@PathVariable("id") Integer productCode){
        return productService.deleteProduct(productCode);
    }
}