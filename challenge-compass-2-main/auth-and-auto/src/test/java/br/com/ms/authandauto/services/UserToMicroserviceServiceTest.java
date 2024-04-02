package br.com.ms.authandauto.services;

import br.com.ms.authandauto.repositories.UserToMicroserviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class UserToMicroserviceServiceTest {
  @InjectMocks
  private UserToMicroserviceService userToMicroserviceService;
  @Mock
  private UserToMicroserviceRepository userToMicroserviceRepository;
  @Spy
  private ModelMapper modelMapper;

  private static final String USER_TO_MICROSERVICE =
          "payloads/userToMicroservice/userToMicroservice.json";

  @Test
  void existsUserBoundWithMicroservice() {
  }

  @Test
  void bindUserToMicroservice() {
    //given
    //then
    //verify
  }

  @Test
  void changeUserRole() {
  }
}