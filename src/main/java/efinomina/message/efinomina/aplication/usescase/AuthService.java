package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.LoginRequestDto;
import efinomina.message.efinomina.aplication.dto.LoginResponseDto;
import efinomina.message.efinomina.infraestructure.mapper.UserLogin;
import efinomina.message.efinomina.infraestructure.persistence.entity.User;
import efinomina.message.efinomina.infraestructure.persistence.entity.UserRole;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryUser;
import efinomina.message.efinomina.infraestructure.persistence.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RepositoryUser userRepository;

    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final UserLogin userLogin;

    public LoginResponseDto login(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())

                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean valid = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!valid) {

            throw new RuntimeException("Credenciales inválidas");

        }

        List<UserRole> roles = userRoleRepository.findByUserId(user.getId());

        String token = jwtService.generate(user);

        return userLogin.toLoginResponse(user, roles, token);

    }

}
