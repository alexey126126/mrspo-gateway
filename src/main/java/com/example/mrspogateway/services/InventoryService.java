package com.example.mrspogateway.services;

import com.example.mrspogateway.dto.InventoryEventDto;
import com.example.mrspogateway.dto.ProductDto;
import com.example.mrspogateway.mappers.ProductMapper;
import com.example.mrspogateway.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final KafkaTemplate<String, InventoryEventDto> kafkaTemplate;
    private final InventoryRepository inventoryRepository;
    private final ProductMapper mapper;
    private static final String TOPIC = "inventory-events";

    /**
     * Обработка события и отправка его в Kafka.
     *
     * @param eventDto - данные о событии
     */
    public void processInventoryEvent(InventoryEventDto eventDto) {
        log.info("Отправка события о товаре {}", eventDto.getProductName());
        kafkaTemplate.send(TOPIC, eventDto);
    }

    public List<ProductDto> viewAllProducts() {
        return mapper.toDtoList(inventoryRepository.findAll());
    }

}
