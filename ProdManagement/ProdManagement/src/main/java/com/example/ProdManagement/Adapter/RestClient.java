//package com.example.ProdManagement.Adapter;
//
//import com.example.ProdManagement.Data.Entity.ProductData;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class RestClient {
//    private static final String GET_ALL_PRODUCTS_API = "http://localhost:8080/api/products";
//    private static final String GET_PRODUCT_BY_ID_API = "http://localhost:8080/api/products/{id}";
//    private static final String CREATE_PRODUCT_API = "http://localhost:8080/api/products";
//    private static final String UPDATE_PRODUCTS_API = "http://localhost:8080/api/products/{id}";
//    private static final String DELETE_PRODUCTS_API = "http://localhost:8080/api/products/{id}";
//
//    // creating rest template class object
//    static RestTemplate restTemplate = new RestTemplate();
//    public static void main(String[] args) {
//        callGetAllProductsAPI();
//        callGetProductByIdAPI();
//        // callCreateProductAPI();                      // error
//        callUpdateProductAPI();
//        callDeleteProductAPI();
//    }
//
//    // creating methods for each rest api
//    // this method() is for rest client to consume Get all products rest api
//
//    // GET ALL
//    private static void callGetAllProductsAPI(){
//        // first create a Http header
//        HttpHeaders headers = new HttpHeaders();
//        // client expects json format from rest api
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);          // passing headers to HttpEntity
//        // restTemplate.exchange(url,method,requestEntity,responseType);
//        // storing result of exchange method in ResponseEntity<String> result
//        ResponseEntity<String> result = restTemplate.exchange(GET_ALL_PRODUCTS_API, HttpMethod.GET,entity, String.class);
//        System.out.println(result);
//    }
//
//    // GET BY ID
//    private static void callGetProductByIdAPI(){
//        Map<String,Integer> param = new HashMap<>();           // hashmap having key(string) and value(integer) pair
//        param.put("id",16);              // retrieving product with id 1
//        // restTemplate.getForObject(url,responseType,uriVariables);
//        ProductData productData = restTemplate.getForObject(GET_PRODUCT_BY_ID_API, ProductData.class,param);
//        System.out.print(productData.getProductCode() + " -> " + productData.getProductName() + " -> " +
//                productData.getProductDescription() + " -> " + productData.getProductType());
//        System.out.println();
//    }
//
//    // POST
//    private static void callCreateProductAPI(){
//        Date createdAt = new Date();
//        Date modifiedAt = new Date();
//        ProductData productData = new ProductData(30,"Coffee","Nestle Coffee","Frozen",true,createdAt,modifiedAt);
//        // restTemplate.postForEntity(url,request,responseType);
//        // postForEntity used to post, returns a response, stored result of postForEntity in ResponseEntity
//        ResponseEntity<ProductData> productData2 = restTemplate.postForEntity(CREATE_PRODUCT_API,productData,ProductData.class);
//        System.out.println(productData2.getBody());
//    }
//
//    // PUT
//    private static void callUpdateProductAPI(){
//        Map<String,Integer> param = new HashMap<>();
//        param.put("id",22);
//        Date createdAt = new Date();
//        Date modifiedAt = new Date();
//        ProductData updateProduct = new ProductData(31,"Laptop","MACBOOK","Frozen",true,createdAt,modifiedAt);
//        // restTemplate.postForEntity(url,request,responseType);
//        restTemplate.put(UPDATE_PRODUCTS_API,updateProduct,param);
//    }
//
//    // DELETE
//    private static void callDeleteProductAPI(){
//        Map<String,Integer> param = new HashMap<>();
//        param.put("id",23);
//        // restTemplate.delete(url,uriVariables);
//        restTemplate.delete(DELETE_PRODUCTS_API,param);
//    }
//}