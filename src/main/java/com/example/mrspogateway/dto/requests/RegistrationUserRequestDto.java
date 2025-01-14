package com.example.mrspogateway.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Schema(description = "User DTO для регистрации")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationUserRequestDto {

    @Schema(description = "Уникальное имя пользователя", example = "user__1")
    @NotBlank(message = "Имя пользователя является обязательным")
    @Size(min = 4, max = 20, message = "Длина поля должна быть от 6 до 20 символов")
    String username;

    @Schema(description = "Пароль пользователя", example = "12345678")
    @NotBlank(message = "Пароль является обязательным")
    @Size(min = 4, message = "Размер пароля должен быть не меньше 8 символов")
    String password;
}
