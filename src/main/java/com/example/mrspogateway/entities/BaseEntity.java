package com.example.mrspogateway.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created_at", nullable = false/*, updatable = false*/)
    OffsetDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    OffsetDateTime updatedAt;

//    @PrePersist
//    protected void onCreate() {
//        createdAt = OffsetDateTime.now();
//        updatedAt = OffsetDateTime.now();
//    }
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = OffsetDateTime.now();
//    }
}
