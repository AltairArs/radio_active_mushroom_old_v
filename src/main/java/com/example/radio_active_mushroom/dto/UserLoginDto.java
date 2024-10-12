package com.example.radio_active_mushroom.dto;

import com.example.radio_active_mushroom.constraints.OnlyOneNotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.radio_active_mushroom.models.UserEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@OnlyOneNotNull(
        message = "Должно быть заполнено только одно: Email, Ник",
        fields = {
                "username",
                "email"
        }
)
public class UserLoginDto implements Serializable {
    @Size(max = 255)
    private String username;
    @Size(min = 8, max = 255)
    @NotBlank
    private String password;
    @Size(max = 255)
    @Email
    private String email;
}