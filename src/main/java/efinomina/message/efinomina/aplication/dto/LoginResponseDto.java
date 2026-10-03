package efinomina.message.efinomina.aplication.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
public class LoginResponseDto {
    private String token;

    private String email;

    private List<String> roles;
}
