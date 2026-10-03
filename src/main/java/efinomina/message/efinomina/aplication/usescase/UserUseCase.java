package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.UserDTO;
import efinomina.message.efinomina.domain.model.entity.User;
import efinomina.message.efinomina.infraestructure.mapper.UserMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserUseCase {
    private final RepositoryUser repositoryUser;

    public UserUseCase(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public UserDTO crearUsuario(UserDTO userDTO) {
        User usuario = new User();
        usuario.setName(userDTO.getName());
        usuario.setLastName(userDTO.getLastName());
        usuario.setEmail(userDTO.getEmail());
        usuario.setPassword(userDTO.getPassword());
        usuario.setPhone(userDTO.getPhone());
        usuario.setActive(true);
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setUpdatedAt(LocalDateTime.now());

        efinomina.message.efinomina.infraestructure.persistence.entity.User usuarioEntity =
            UserMapper.toEntity(usuario);
        efinomina.message.efinomina.infraestructure.persistence.entity.User usuarioSaved =
            repositoryUser.save(usuarioEntity);
        return UserMapper.toDTO(UserMapper.toDomain(usuarioSaved));
    }

    public Optional<UserDTO> obtenerUsuarioPorId(Long id) {
        return repositoryUser.findById(id)
                .map(user -> UserMapper.toDTO(UserMapper.toDomain(user)));
    }

    public List<UserDTO> obtenerTodosLosUsuarios() {
        return repositoryUser.findAll()
                .stream()
                .map(user -> UserMapper.toDTO(UserMapper.toDomain(user)))
                .collect(Collectors.toList());
    }

    public UserDTO actualizarUsuario(Long id, UserDTO userDTO) {
        return repositoryUser.findById(id).map(usuario -> {
            usuario.setName(userDTO.getName());
            usuario.setLastName(userDTO.getLastName());
            usuario.setEmail(userDTO.getEmail());
            usuario.setPassword(userDTO.getPassword());
            usuario.setPhone(userDTO.getPhone());
            usuario.setUpdatedAt(LocalDateTime.now());
            return UserMapper.toDTO(UserMapper.toDomain(repositoryUser.save(usuario)));
        }).orElse(null);
    }

    public void eliminarUsuario(Long id) {
        repositoryUser.deleteById(id);
    }
}
