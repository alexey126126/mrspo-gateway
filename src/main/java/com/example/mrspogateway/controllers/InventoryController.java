package com.example.mrspogateway.controllers;


import com.example.mrspogateway.dto.InventoryEventDto;
import com.example.mrspogateway.dto.ProductDto;
import com.example.mrspogateway.services.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/inventory")
@Validated
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;

    /**
     * Endpoint для обработки событий о товарах на складе.
     *
     * @param eventDto - данные о событии
     * @return ResponseEntity с результатом обработки
     */
    @PostMapping("/event")
    public ResponseEntity<Map<String, String>> handleInventoryEventUser(@Valid @RequestBody InventoryEventDto eventDto) {
        try {
            inventoryService.processInventoryEvent(eventDto);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(Map.of("message", "Event processed successfully"));
    }

    @GetMapping("/products")
    @Operation(summary = "Страница товаров", description = "Позволяет посмотреть список товаров")
    public ResponseEntity<List<ProductDto>> viewAllProducts() {
        log.debug("InventoryController#viewAllProducts");
        return ResponseEntity.ok(
                inventoryService.viewAllProducts()
        );
    }
}
