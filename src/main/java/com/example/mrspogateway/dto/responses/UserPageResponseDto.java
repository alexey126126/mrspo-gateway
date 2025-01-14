package com.example.mrspogateway.dto.responses;

import com.example.mrspogateway.dto.common.ComplexErrorDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;


@Schema(description = "User DTO для получения информации о нем")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPageResponseDto {

    @Schema(description = "id пользователя", example = "123441")
    Long id;
    @Schema(description = "Имя пользователя", example = "user_name")
    String username;
    @Schema(description = "Роли пользователя", example = "[USER_ROLE]")
    Collection<String> roles;

    @Schema(description = "Описание ошибки")
    ComplexErrorDto error;
}
