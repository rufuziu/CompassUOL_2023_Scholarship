package br.demattos.iury.msproposal.repositories;

import br.demattos.iury.msproposal.entities.Proposal;
import br.demattos.iury.msproposal.enums.EResult;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

import static br.demattos.iury.msproposal.common.ProposalConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProposalRepositoryTest {
  @Autowired
  private ProposalRepository repository;
  @Autowired
  private TestEntityManager testEntityManager;
  @Spy
  private ModelMapper mapper;


  @Test
  void savePool_withValidData_ReturnsPool() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Proposal entity = mapper.map(VALID_PROPOSALDTO_1, Proposal.class);
    entity.setInitTime(now);
    //then
    Proposal sut = repository.save(entity);
    //assert
    assertAll("PoolRepository save valid pool",
            () -> assertEquals(7L, sut.getId()),
            () -> assertEquals(entity.getDescription(),
                    sut.getDescription()),
            () -> assertEquals(entity.getCloseTime(),
                    sut.getCloseTime())
    );
  }

  @Test
  void savePool_withInvalidData_ThrowsDataIntegrityViolationException() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Proposal entity = mapper.map(INVALID_PROPOSALDTO, Proposal.class);
    entity.setInitTime(now);
    //then
    //assert
    assertThrows(DataIntegrityViolationException.class,
            () -> repository.save(entity));
  }

  @Test
  void savePool_withExistingData_ThrowsDataIntegrityViolationException() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Proposal entity = mapper.map(VALID_PROPOSALDTO_4, Proposal.class);
    entity.setInitTime(now);
    //then
    Proposal savedPool = testEntityManager.persistAndFlush(entity);
    testEntityManager.detach(savedPool);
    savedPool.setId(null);
    //assert
    assertThrows(DataIntegrityViolationException.class,
            () -> repository.save(savedPool));
  }

  @Test
  void existsPool_ByCloseTimeLessThanTime_ReturnsTrue() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Proposal entity = mapper.map(VALID_PROPOSALDTO_1, Proposal.class);
    entity.setInitTime(now);
    LocalDateTime time = VALID_PROPOSALDTO_1
            .getCloseTime()
            .plusMinutes(10);
    //then
    Proposal savedPool = repository.save(entity);
    //assert
    assertTrue(repository.existsByCloseTimeLessThan(time));
  }

  @Test
  void notExistsPool_ByCloseTimeLessThanTime_ReturnsFalse() {
    //given
    LocalDateTime time = VALID_PROPOSALDTO_1
            .getCloseTime()
            .plusMinutes(10);
    //then
    //assert
    assertFalse(repository.existsByCloseTimeLessThan(time));
  }

  @Test
  void findAllByCloseTimeLessThan_ReturnsPoolList() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Proposal entity1 = mapper.map(VALID_PROPOSALDTO_1, Proposal.class);
    Proposal entity2 = mapper.map(VALID_PROPOSALDTO_2, Proposal.class);
    Proposal entity3 = mapper.map(VALID_PROPOSALDTO_3, Proposal.class);
    Proposal entity4 = mapper.map(VALID_PROPOSALDTO_4, Proposal.class);
    entity1.setInitTime(now);
    entity2.setInitTime(now);
    entity3.setInitTime(now);
    entity4.setInitTime(now);

    LocalDateTime time = VALID_PROPOSALDTO_1
            .getCloseTime()
            .plusMinutes(10);
    //then
    repository.save(entity1);
    repository.save(entity2);
    repository.save(entity3);
    repository.save(entity4);
    List<Proposal> list = repository
            .findAllByResultAndCloseTimeLessThan(EResult.POLLING, time);
    //assert
    assertAll("PoolRepository find all closed proposals",
            () -> assertEquals(4, list.size()),
            () -> assertEquals(5L, list.get(3).getId())
    );
  }
}