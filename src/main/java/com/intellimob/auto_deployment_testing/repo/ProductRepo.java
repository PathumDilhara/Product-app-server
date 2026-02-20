package com.intellimob.auto_deployment_testing.repo;

import com.intellimob.auto_deployment_testing.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
}
