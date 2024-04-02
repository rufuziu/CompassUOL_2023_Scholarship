package br.demattos.iury.msproposal.services;

import br.demattos.iury.msproposal.dtos.ProposalDTO;
import br.demattos.iury.msproposal.dtos.ProposalNewDTO;
import br.demattos.iury.msproposal.entities.Proposal;
import br.demattos.iury.msproposal.repositories.ProposalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.demattos.iury.msproposal.common.ProposalConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProposalServiceTest {
  @InjectMocks
  private ProposalService service;
  @Spy
  private ModelMapper mapper;
  @Mock
  private ProposalRepository repository;

  @Test
  void createProposal_withCloseTimeProposalDTO_ReturnsProposalDTO() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Proposal entity = mapper.map(VALID_PROPOSALDTO_1, Proposal.class);
    entity.setId(1L);
    entity.setInitTime(now);
    when(repository.save(any())).thenReturn(entity);
    //then
    ProposalNewDTO sut = service.createProposal(VALID_PROPOSALDTO_1);
    //assert
    assertAll("PoolService create pool with closeTime",
            () -> assertEquals(1L,
                    sut.getId()),
            () -> assertEquals(VALID_PROPOSALDTO_1.getDescription(),
                    sut.getDescription()),
            () -> assertEquals(VALID_PROPOSALDTO_1.getCloseTime(),
                    sut.getCloseTime()));

  }

  @Test
  void createProposal_withNullCloseTimeProposalDTO_ReturnsProposalDTO() {
    //given
    LocalDateTime now = LocalDateTime.now();
    Proposal entity = mapper.map(VALID_PROPOSALDTO_WITHOUTCLOSETIME,
            Proposal.class);
    entity.setInitTime(now);
    entity.setCloseTime(now);
    entity.setId(1L);
    when(repository.save(any())).thenReturn(entity);
    //then
    ProposalNewDTO sut = service
            .createProposal(VALID_PROPOSALDTO_WITHOUTCLOSETIME);
    //assert
    assertAll("PoolService create pool with null closeTime",
            () -> assertEquals(1L,
                    sut.getId()),
            () -> assertEquals(VALID_PROPOSALDTO_WITHOUTCLOSETIME.getDescription(),
                    sut.getDescription()),
            () -> assertEquals(now,
                    sut.getCloseTime()));

  }

  @Test
  void createProposal_withInvalidProposalDTO_ThrowsIllegalArgumentException() {
    //given
    //then
    //assert
    assertThrows(IllegalArgumentException.class,
            () -> service
                    .createProposal(INVALID_PROPOSALDTO));
  }

  @Test
  void getAllOpenedProposals_ReturnsProposalDTOList() {
    List<Proposal> list = new ArrayList<>();
    list.add(mapper.map(VALID_PROPOSALDTO_1, Proposal.class));
    list.add(mapper.map(VALID_PROPOSALDTO_2, Proposal.class));
    list.add(mapper.map(VALID_PROPOSALDTO_3, Proposal.class));
    list.add(mapper.map(VALID_PROPOSALDTO_4, Proposal.class));

    when(repository.findAllByResultAndCloseTimeLessThan(any(),
            any())).thenReturn(list);
    //then
    List<ProposalDTO> sut = service.getAllEndedProposals();

    //assert
    assertAll("PoolRepository find all opened proposals",
            () -> assertEquals(4, sut.size()));
  }

  @Test
  void haveProposalEnded_ReturnsTrue() {
    when(repository.existsByCloseTimeLessThan(any()))
            .thenReturn(true);
    assertTrue(service.hasAnyProposalEnded());
  }

  @Test
  void haveProposalEnded_ReturnsFalse() {
    when(repository.existsByCloseTimeLessThan(any()))
            .thenReturn(false);
    assertFalse(service.hasAnyProposalEnded());
  }

  @Test
  void getAllEndedProposals_ReturnsPoolDTOList() {
    List<Proposal> list = new ArrayList<>();
    list.add(mapper.map(VALID_PROPOSALDTO_1, Proposal.class));
    list.add(mapper.map(VALID_PROPOSALDTO_2, Proposal.class));
    list.add(mapper.map(VALID_PROPOSALDTO_3, Proposal.class));
    list.add(mapper.map(VALID_PROPOSALDTO_4, Proposal.class));
    when(repository.findAllByResultAndCloseTimeLessThan(any(), any()))
            .thenReturn(list);

    List<ProposalDTO> sut = service.getAllEndedProposals();
    assertAll("PoolRepository find all closed proposals",
            () -> assertEquals(4, sut.size()));
  }

}