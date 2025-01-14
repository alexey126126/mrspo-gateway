package com.example.mrspogateway.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Детали ошибки валидации")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationInfo {

    @Schema(description = "Поле", example = "password")
    String field;

    @Schema(description = "Проблема валидации", example = "Размер пароля должен быть не меньше 8 символов")
    String description;
}