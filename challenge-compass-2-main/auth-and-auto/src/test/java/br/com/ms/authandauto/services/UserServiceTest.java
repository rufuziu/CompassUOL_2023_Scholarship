package br.com.ms.authandauto.services;

import br.com.ms.authandauto.dtos.user.UserDTO;
import br.com.ms.authandauto.dtos.user.input.UserInDTO;
import br.com.ms.authandauto.dtos.user.output.UserCreatedOutDTO;
import br.com.ms.authandauto.dtos.user.output.UserWithMicroservisesAndRolesOutDTO;
import br.com.ms.authandauto.entities.User;
import br.com.ms.authandauto.exceptions.user.UserEmailAlreadyInUseException;
import br.com.ms.authandauto.repositories.UserRepository;
import br.com.ms.authandauto.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepository userRepository;
  @Spy
  private ModelMapper modelMapper;

  private static final String CREATE_USER = "payloads/user/createUser.json";
  private static final String USERS = "payloads/user/listOfUser.json";
  private static final String USER =
          "payloads/user/user.json";

  @Test
  void createUser() throws IOException {
    //given
    User user = JsonUtils.getObjectFromFile(CREATE_USER, User.class);
    UserInDTO userIn = modelMapper.map(user, UserInDTO.class);
    UserCreatedOutDTO userOut = modelMapper.map(user, UserCreatedOutDTO.class);
    when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
    when(userRepository.save(any())).thenReturn(user);
    //then
    UserCreatedOutDTO response = userService.createUser(userIn);
    //verify
    assertAll("User created Payload",
            () -> assertEquals(1, response.getId()),
            () -> assertEquals("Test", response.getName()),
            () -> assertEquals("test@email.com", response.getEmail()));
  }

  @Test
  void userEmailAlreadyInUse() throws IOException {
    //given
    User user = JsonUtils.getObjectFromFile(CREATE_USER, User.class);
    UserInDTO userIn = modelMapper.map(user, UserInDTO.class);
    when(userRepository.findByEmail(any()))
            .thenReturn(Optional.of(user));
    //then
    assertThrows(UserEmailAlreadyInUseException.class,
            () -> userService.createUser(userIn));
  }

  @Test
  void getAllUsersWithMicroservicesAndRoles() throws IOException {
    //given
    List<User> users = JsonUtils.getListOfObjectFromFile(
            USERS,
            User.class);
    //infinite loop on tables
    when(userRepository.findAll()).thenReturn(users);
    //then
    List<UserWithMicroservisesAndRolesOutDTO> response =
            userService.getAllUsersWithMicroservicesAndRoles();
    //verify
    assertAll("User List Payload",
            () -> assertEquals(2, response.size()),
            () -> assertEquals("user 2",
                    response.get(1).getName()),
            () -> assertEquals("ADMIN",
                    response.get(1).getMicroservices()
                            .get(0).getRoleUser().name())
    );
  }

  @Test
  void getUserById() throws IOException {
    //given
    User user = JsonUtils.getObjectFromFile(USER, User.class);
    when(userRepository.findById(any()))
            .thenReturn(Optional.ofNullable(user));
    //then
    UserDTO response = userService.getUserById(1L);
    //verify
    assertAll("User created Payload",
            () -> assertEquals(1, response.getId()),
            () -> assertEquals("user 1", response.getName()),
            () -> assertEquals("email1@compasso.com.br",
                    response.getEmail())
//            ,
//            () -> assertEquals("ms-a",
//                    response.getMicroservices().get(0).getName())
    );
  }
}