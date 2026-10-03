package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.LoginResponseDto;
import efinomina.message.efinomina.infraestructure.persistence.entity.User;
import efinomina.message.efinomina.infraestructure.persistence.entity.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserLogin {


    public LoginResponseDto toLoginResponse(
            User user,
            List<UserRole> userRoles,
            String token
    )
    {

        List<String> roles =
                userRoles.stream()
                        .map(userRole -> userRole.getRole().getNombre())
                        .toList();

        return LoginResponseDto.builder()
                .email(user.getEmail())
                .roles(roles)
                .token(token)
                .build();

    }

}
