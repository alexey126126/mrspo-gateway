package com.example.mrspogateway.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Общий ответ для обработки ошибок")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComplexErrorDto {

    @Schema(description = "Временная метка ошибки", example = "2024-11-24T17:46:41.115331600")
    LocalDateTime timestamp;

    @Schema(description = "Код состояния HTTP", example = "400")
    int status;

    @Schema(description = "Краткое описание ошибки", example = "Bad Request")
    String error;

    @Schema(description = "Сообщение ошибки", example = "Ошибка валидации параметров запроса")
    String message;

    @Schema(description = "Детали ошибки валидации")
    List<ValidationInfo> validationDetails;

    @Schema(description = "Детали ошибки")
    List<AdditionalInfo> details;


}
