package com.example.mrspogateway.dto.responses;


import com.example.mrspogateway.dto.common.ComplexErrorDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Schema(description = "DTO ответа на запрос аутентификации")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUserResponseDto {

    @Schema(description = "Успещныая ли аутентификация", example = "true")
    Boolean success;
    @Schema(description = "jwt токен пользователя", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0X3VzZXJfMSIsImlhdCI6MTczMjM3MDYxOSwiZXhwIjoxNzMyMzc0MjE5fQ.6fFRF1Y8UnxbVGPMo0vP9tSLFWTIoOIzHb7nb4w-wJI")
    String token;
    @Schema(description = "время жизни токена", example = "360000")
    Long expirationTime;

    @Schema(description = "Описание ошибки")
    ComplexErrorDto error;

}
