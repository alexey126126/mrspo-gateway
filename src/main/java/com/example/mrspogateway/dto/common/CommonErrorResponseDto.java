package com.example.mrspogateway.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ ошибки")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommonErrorResponseDto {

    @Schema(description = "Описание ошибки")
    ComplexErrorDto error;
}
