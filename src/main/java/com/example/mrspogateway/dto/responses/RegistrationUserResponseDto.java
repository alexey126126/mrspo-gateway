package com.example.mrspogateway.dto.responses;


import com.example.mrspogateway.dto.common.ComplexErrorDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Schema(description = "User DTO для регистрации")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationUserResponseDto {

    @Schema(description = "Успещныая ли регистрация", example = "true")
    Boolean success;

    @Schema(description = "Описание ошибки")
    ComplexErrorDto error;
}
