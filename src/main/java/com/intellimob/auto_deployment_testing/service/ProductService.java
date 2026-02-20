package com.intellimob.auto_deployment_testing.service;

import com.intellimob.auto_deployment_testing.dto.CreateDTO;
import com.intellimob.auto_deployment_testing.dto.RetrieveDTO;
import com.intellimob.auto_deployment_testing.entity.ProductEntity;
import com.intellimob.auto_deployment_testing.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepo productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    // Create
    public RetrieveDTO createProduct(CreateDTO dto){
        try {
            // Manual mapping
            ProductEntity entity = new ProductEntity();
            entity.setName(dto.getName());
            entity.setPrice(dto.getPrice());

            // save in db
            productRepo.save(entity);

            RetrieveDTO retrieveDTO = new RetrieveDTO();
            retrieveDTO.setId(entity.getId());
            retrieveDTO.setName(entity.getName());
            retrieveDTO.setPrice(entity.getPrice());

            log.info("### Product saved successfully : {}", entity.getId());
            return retrieveDTO;

        }catch (Exception ex){
            throw new RuntimeException("Error creating product : " + ex.getMessage() );
        }
    }

    // Get all
    public List<RetrieveDTO> getAllProducts(){
        try {
            List<RetrieveDTO> products =  modelMapper.map(
                    productRepo.findAll(),
                    new TypeToken<List<RetrieveDTO>>(){}.getType()
            );

            log.info("### Returning all products : {}",products.toArray().length);
            return products;
        } catch (Exception ex) {
            throw new RuntimeException("Error getting all products : "+ ex.getMessage());
        }
    }

    // Get by id
    public RetrieveDTO getProductById(Long id){
        try {
            RetrieveDTO dto = modelMapper.map(productRepo.findById(id), RetrieveDTO.class);
            log.info("### Product found : {}",dto.getId() );
            return  dto;
        } catch (Exception ex) {
            throw new RuntimeException("Error getting all products : "+ ex.getMessage());
        }
    }

    // Update product by id
    public RetrieveDTO updateProductById(Long id, CreateDTO dto){
        try {
            ProductEntity entity = productRepo.findById(id)
                    .orElseThrow( () -> new RuntimeException("Product not found with id : "+id));

            modelMapper.map(dto, entity);

            ProductEntity newProduct = productRepo.save(entity);

            log.info("Product updated id : {}", entity.getId());
            return modelMapper.map(newProduct, RetrieveDTO.class);
        } catch (Exception ex) {
            throw new RuntimeException("Error getting all products : "+ ex.getMessage());
        }
    }

    // Delete By id
    public void deleteProductById (Long id){
        try {
            ProductEntity product = productRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not dound with id : "+ id));
            productRepo.deleteById(product.getId());
            log.info("### Product deleted : {}", id);
        } catch (Exception ex) {
            throw new RuntimeException("Error getting all products : "+ ex.getMessage());
        }
    }
}
