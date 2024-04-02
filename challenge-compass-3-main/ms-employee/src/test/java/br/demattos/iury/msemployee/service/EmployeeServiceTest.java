package br.demattos.iury.msemployee.service;

import br.demattos.iury.msemployee.dto.EmployeeDTO;
import br.demattos.iury.msemployee.entity.Employee;
import br.demattos.iury.msemployee.exceptions.employee_exce.EmployeeCpfAlreadyInUseExcepetion;
import br.demattos.iury.msemployee.exceptions.employee_exce.EmployeeCpfNotFound;
import br.demattos.iury.msemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static br.demattos.iury.msemployee.common.EmployeeConstants.VALID_EMPLOYEEDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
  @InjectMocks
  private EmployeeService service;
  @Spy
  private ModelMapper mapper;
  @Mock
  private EmployeeRepository repository;

  @Test
  void createEmployee_withCpfNotUsed_ReturnsEmployeeDTO() {
    //given
    Employee entity = mapper.map(VALID_EMPLOYEEDTO, Employee.class);
    entity.setId(1L);
    when(repository.save(any())).thenReturn(entity);
    //then
    EmployeeDTO sut = service
            .createEmployee(VALID_EMPLOYEEDTO);
    //verify
    assertAll("UserService create method",
            () -> assertEquals(1L, sut.getId()),
            () -> assertEquals(VALID_EMPLOYEEDTO.getCpf(), sut.getCpf()));
  }

  @Test
  void createEmployee_withCpfAlreadyInUse_ThrowsEmployeeCpfAlreadyInUseExcepetion() {
    //given
    when(repository.existsByCpf(VALID_EMPLOYEEDTO.getCpf()))
            .thenThrow(EmployeeCpfAlreadyInUseExcepetion.class);
    //then
    //verify
    assertThrows(EmployeeCpfAlreadyInUseExcepetion.class,
            () -> service.createEmployee(VALID_EMPLOYEEDTO));
  }

  @Test
  void employeeCanVote_withCpfRegisteredInMicroservice_ReturnsTrue() {
    //given
    when(repository.existsByCpf(VALID_EMPLOYEEDTO.getCpf()))
            .thenReturn(true);
    //then
    //verify
    assertTrue(service.employeeCanVote(VALID_EMPLOYEEDTO.getCpf()));
  }

  @Test
  void employeeCanVote_withCpfNotRegisteredInMicroservice_ThrowsEmployeeCpfNotFound() {
    //given
    when(repository.existsByCpf(VALID_EMPLOYEEDTO.getCpf()))
            .thenThrow(EmployeeCpfNotFound.class);
    //then
    //verify
    assertThrows(EmployeeCpfNotFound.class,
            () -> service.employeeCanVote(VALID_EMPLOYEEDTO.getCpf()));
  }
}