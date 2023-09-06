package com.example.ProdManagement.Controller;

import com.example.ProdManagement.Data.Entity.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

// Using REST TEMPLATE for CRUD operations
@RestController
public class MyNewController {
    @Autowired
    RestTemplate restTemplate;

    // GET
    @RequestMapping(value = "/template/products",method = RequestMethod.GET)
    public String getProductList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(" http://localhost:8080/api/products", HttpMethod.GET, entity, String.class).getBody();
    }

    // POST
    @RequestMapping(value = "/template/products", method = RequestMethod.POST)
    public String createProducts(@RequestBody ProductData productData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductData> entity = new HttpEntity<ProductData>(productData,headers);

        return restTemplate.exchange(
                "http://localhost:8080/api/products", HttpMethod.POST, entity, String.class).getBody();
    }

    // PUT
    @RequestMapping(value = "/template/products/{id}", method = RequestMethod.PUT)
    public String updateProduct(@PathVariable("id") String id, @RequestBody ProductData productData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductData> entity = new HttpEntity<ProductData>(productData,headers);

        return restTemplate.exchange(
                "http://localhost:8080/api/products/"+id, HttpMethod.PUT, entity, String.class).getBody();
    }

    // DELETE
    @RequestMapping(value = "/template/products/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable("id") Integer productCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductData> entity = new HttpEntity<ProductData>(headers);

        return restTemplate.exchange(
                "http://localhost:8080/api/products/" + productCode, HttpMethod.DELETE, entity, String.class).getBody();
    }
}
