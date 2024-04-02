package br.com.ms.authandauto.services;

import br.com.ms.authandauto.dtos.microservice.MicroserviceDTO;
import br.com.ms.authandauto.entities.Microservice;
import br.com.ms.authandauto.exceptions.microservice.MicroserviceNotFoundException;
import br.com.ms.authandauto.repositories.MicroserviceRepository;
import br.com.ms.authandauto.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MicroserviceServiceTest {
  @InjectMocks
  private MicroserviceService microserviceService;
  @Mock
  private MicroserviceRepository microserviceRepository;
  @Spy
  private ModelMapper modelMapper;
  private static final String MICROSERVICE = "payloads/microservice/microservice.json";

  @Test
  void getMicroserviceById() throws IOException {
    //given
    Microservice ms = JsonUtils.getObjectFromFile(MICROSERVICE,
            Microservice.class);
    when(microserviceRepository.findById(any()))
            .thenReturn(Optional.of(ms));
    //then
    MicroserviceDTO response = microserviceService
            .getMicroserviceById(1L);
    //verify
    assertAll("Microservice findById Payload",
            () -> assertEquals(1, response.getId()),
            () -> assertEquals("ms-test", response.getName()));
  }

  @Test
  void microserviceNotFound() throws IOException {
    //given
    when(microserviceRepository.findById(any()))
            .thenReturn(Optional.empty());
    //then
    assertThrows(MicroserviceNotFoundException.class,
            () -> microserviceService
                    .getMicroserviceById(1L));
  }
}