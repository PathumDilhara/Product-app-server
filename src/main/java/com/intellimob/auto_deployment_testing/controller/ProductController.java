package com.intellimob.auto_deployment_testing.controller;

import com.intellimob.auto_deployment_testing.dto.CreateDTO;
import com.intellimob.auto_deployment_testing.dto.RetrieveDTO;
import com.intellimob.auto_deployment_testing.response.CustomResponse;
import com.intellimob.auto_deployment_testing.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get /api/v1/product
    @GetMapping
    public CustomResponse<List<RetrieveDTO>> getAllProducts(){
        List<RetrieveDTO> products = productService.getAllProducts();
        return new CustomResponse<>(true, "All Products Returned", products);
    }

    // Get /api/v1/product/id
    @GetMapping("/{id}")
    public CustomResponse<RetrieveDTO> getProductById(@PathVariable Long id){
        RetrieveDTO retrieveDTO = productService.getProductById(id);
        return new CustomResponse<>(true, "Product "+id+"Returned", retrieveDTO);
    }

    // Post /api/v1/product
    @PostMapping()
    public CustomResponse<RetrieveDTO> createProduct (@Valid @RequestBody CreateDTO createDTO){
        RetrieveDTO retrieveDTO = productService.createProduct(createDTO);
        return new CustomResponse<>(true, "Product create successfully"+retrieveDTO.getId(), retrieveDTO);
    }

    // PUT /api/v1/product/id
    @PutMapping("/{id}")
    public CustomResponse<RetrieveDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody CreateDTO createDTO){
        RetrieveDTO retrieveDTO = productService.updateProductById(id, createDTO);
        return new CustomResponse<>(true, "Product "+id+"Returned", retrieveDTO);
    }

    // DELETE /api/v1/product/id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
