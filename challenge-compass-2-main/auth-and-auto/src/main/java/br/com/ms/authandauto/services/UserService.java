package br.com.ms.authandauto.services;

import br.com.ms.authandauto.dtos.microservice.output.MicroserviceAndUserRoleOutDTO;
import br.com.ms.authandauto.dtos.user.UserDTO;
import br.com.ms.authandauto.dtos.user.input.UserInDTO;
import br.com.ms.authandauto.dtos.user.output.UserCreatedOutDTO;
import br.com.ms.authandauto.dtos.user.output.UserWithMicroservisesAndRolesOutDTO;
import br.com.ms.authandauto.entities.User;
import br.com.ms.authandauto.exceptions.user.UserEmailAlreadyInUseException;
import br.com.ms.authandauto.exceptions.user.UserNotFoundException;
import br.com.ms.authandauto.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  ModelMapper modelMapper;

  public UserCreatedOutDTO createUser(UserInDTO userInDto) {
    if (userRepository
            .findByEmail(userInDto.getEmail())
            .isPresent()) {
      String message = new StringBuilder()
              .append("Email ")
              .append(userInDto.getEmail())
              .append(" is already in use.")
              .toString();
      throw new UserEmailAlreadyInUseException(message);
    } else {
      User user = modelMapper.map(userInDto, User.class);
      return modelMapper.map(userRepository.save(user),
              UserCreatedOutDTO.class);
    }
  }

  public List<UserWithMicroservisesAndRolesOutDTO>
  getAllUsersWithMicroservicesAndRoles() {
    List<User> users = userRepository.findAll();
    List<UserWithMicroservisesAndRolesOutDTO> usersDto =
            new ArrayList<>();
    users.forEach(u -> {
      UserWithMicroservisesAndRolesOutDTO uDto =
              new UserWithMicroservisesAndRolesOutDTO(u);
      u.getRelationships()
              .forEach(ms -> {
                MicroserviceAndUserRoleOutDTO msDto = new
                        MicroserviceAndUserRoleOutDTO(
                        ms.getMicroservice().getName(),
                        ms.getUserRole());
                uDto.getMicroservices().add(msDto);
              });
      usersDto.add(uDto);
    });
    return usersDto;
  }

  public UserDTO getUserById(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      String message = new StringBuilder()
              .append("User not found. id: ")
              .append(id).toString();
      throw new UserNotFoundException(message);
    } else
      return modelMapper.map(user.get(), UserDTO.class);
  }
}
