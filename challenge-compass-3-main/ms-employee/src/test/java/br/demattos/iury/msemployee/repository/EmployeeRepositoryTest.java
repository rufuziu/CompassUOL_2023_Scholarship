package br.demattos.iury.msemployee.repository;

import br.demattos.iury.msemployee.entity.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import static br.demattos.iury.msemployee.common.EmployeeConstants.VALID_EMPLOYEEDTO;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {
  @Autowired
  private EmployeeRepository repository;
  @Autowired
  private TestEntityManager testEntityManager;
  @Spy
  private ModelMapper mapper;

  @Test
  void createEmployee_withCpfNotUsed_ReturnsEmployee() {
    //given
    Employee entity = mapper.map(VALID_EMPLOYEEDTO, Employee.class);
    //then
    Employee savedEmp = repository.save(entity);
    //assert
    assertAll("EmployeeRepository saving employee",
            () -> assertEquals(3L, savedEmp.getId()),
            () -> assertEquals(VALID_EMPLOYEEDTO.getCpf(),
                    savedEmp.getCpf())
    );
  }

  @Test
  void createEmployee_withCpfAlreadyInUse_ThrowsEmployeeCpfAlreadyInUseExcepetion() {
    //given
    Employee entity = mapper.map(VALID_EMPLOYEEDTO, Employee.class);
    //then
    Employee savedEmp = testEntityManager.persistAndFlush(entity);
    testEntityManager.detach(savedEmp);
    savedEmp.setId(null);
    //assert
    assertThrows(DataIntegrityViolationException.class,
            () -> repository.save(savedEmp));
  }

  @Test
  void existsEmployee_byCpfRegisteredInMicroservice_ReturnsTrue() {
    //given
    Employee entity = mapper.map(VALID_EMPLOYEEDTO, Employee.class);
    repository.save(entity);
    //then
    //assert
    assertTrue(repository.existsByCpf(VALID_EMPLOYEEDTO.getCpf()));
  }

  @Test
  void existsEmployee_byCpfNotRegisteredInMicroservice_ReturnsFalse() {
    //given
    //then
    //assert
    assertFalse(repository.existsByCpf(VALID_EMPLOYEEDTO.getCpf()));
  }
}