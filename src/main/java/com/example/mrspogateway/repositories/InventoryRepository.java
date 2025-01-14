package com.example.mrspogateway.repositories;

import com.example.mrspogateway.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<ProductEntity, Long> {
}
