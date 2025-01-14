package com.example.mrspogateway.mappers;

import com.example.mrspogateway.dto.ProductDto;
import com.example.mrspogateway.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    public Page<ProductDto> toDtoPage(Page<ProductEntity> productPage) {
        List<ProductDto> productDtos = productPage.stream()
                .map(this::toDto)
                .toList();

        return new PageImpl<>(
                productDtos,
                productPage.getPageable(),
                productPage.getTotalElements()
        );
    }

    public List<ProductDto> toDtoList(List<ProductEntity> productPage) {
        return productPage.stream()
                .map(this::toDto)
                .toList();
    }

    public ProductDto toDto(ProductEntity entity) {
        return ProductDto.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .build();
    }
}
