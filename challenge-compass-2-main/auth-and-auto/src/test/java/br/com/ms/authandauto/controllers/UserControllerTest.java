package br.com.ms.authandauto.controllers;

import br.com.ms.authandauto.dtos.user.input.UserInDTO;
import br.com.ms.authandauto.dtos.user.output.UserCreatedOutDTO;
import br.com.ms.authandauto.entities.User;
import br.com.ms.authandauto.services.UserService;
import br.com.ms.authandauto.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class UserControllerTest {
  @InjectMocks
  private UserController userController;
  @Mock
  private UserService userService;
  @Spy
  private ModelMapper modelMapper;
  private MockMvc mockMvc;
  private static final String USER = "payloads/user/createUser.json";
  @BeforeEach
  void setup(){
    mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  void createUser() throws Exception {
    //given
    String payload = JsonUtils.readFileAsString(USER);
    User user = JsonUtils.getObjectFromFile(USER, User.class);
    UserInDTO userIn = modelMapper.map(USER, UserInDTO.class);
    UserCreatedOutDTO userOut = modelMapper.map(USER, UserCreatedOutDTO.class);
    when(userService.createUser(any())).thenReturn(userOut);
    //then
    MockHttpServletRequestBuilder builder = post("/api/users/")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON);
    mockMvc.perform(builder)
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void getAllUsersWithMicroservicesAndRoles() {
  }

  @Test
  void bindUserToService() {
  }

  @Test
  void changeUserRole() {
  }
}