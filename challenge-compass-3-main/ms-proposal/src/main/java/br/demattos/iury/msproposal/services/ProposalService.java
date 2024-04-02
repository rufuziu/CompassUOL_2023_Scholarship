package br.demattos.iury.msproposal.services;

import br.demattos.iury.msproposal.dtos.ProposalDTO;
import br.demattos.iury.msproposal.dtos.ProposalNewDTO;
import br.demattos.iury.msproposal.dtos.ProposalResultDTO;
import br.demattos.iury.msproposal.entities.Proposal;
import br.demattos.iury.msproposal.enums.EResult;
import br.demattos.iury.msproposal.exceptions.proposal_exce.ProposalAlreadyExistsException;
import br.demattos.iury.msproposal.exceptions.proposal_exce.ProposalNotExistsException;
import br.demattos.iury.msproposal.repositories.ProposalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProposalService {
  private ModelMapper mapper;
  private ProposalRepository repository;

  @Value("${proposalTime}")
  private int minutes;

  public ProposalService(ModelMapper mapper,
                         ProposalRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  public ProposalNewDTO createProposal(ProposalNewDTO proposalNewDTO) {
    if (repository
            .findByDescription(proposalNewDTO.getDescription())
            .isPresent()) {
      throw new ProposalAlreadyExistsException("Description already in use.");
    }
    proposalNewDTO.setId(null);
    Proposal proposal = mapper.map(proposalNewDTO, Proposal.class);
    var now = LocalDateTime.now();
    proposal.setInitTime(now);
    if (proposal.getCloseTime() == null ||
            proposal.getCloseTime().isBefore(proposal.getInitTime()))
      proposal.setCloseTime(now.plusMinutes(minutes));

    return mapper.map(repository.save(proposal), ProposalNewDTO.class);
  }

  public List<ProposalNewDTO> getPollingProposals() {
    return Arrays.asList(mapper.map(
            repository.findAllByResult(EResult.POLLING)
            , ProposalNewDTO[].class));
  }

  public List<ProposalResultDTO> getAllDrawResult() {
    return Arrays.asList(mapper.map(
            repository.findAllByResult(EResult.DRAW)
            , ProposalResultDTO[].class));
  }

  public List<ProposalResultDTO> getAllApprovedResult() {
    return Arrays.asList(mapper.map(
            repository.findAllByResult(EResult.APPROVED)
            , ProposalResultDTO[].class));
  }

  public List<ProposalResultDTO> getAllRejectedResult() {
    return Arrays.asList(mapper.map(
            repository.findAllByResult(EResult.REJECTED)
            , ProposalResultDTO[].class));
  }

  public ProposalResultDTO getById(Long id) {
    Optional<Proposal> prop = repository.findById(id);
    if (prop.isEmpty()) {
      throw new ProposalNotExistsException("Proposal not exists");
    } else {
      return mapper.map(prop.get(), ProposalResultDTO.class);
    }
  }

  public Boolean hasAnyProposalEnded() {
    return repository.existsByCloseTimeLessThan(LocalDateTime.now());
  }

  public List<ProposalDTO> getAllEndedProposals() {
    List<Proposal> list = repository
            .findAllByResultAndCloseTimeLessThan(EResult.POLLING,
                    LocalDateTime.now());
    return Arrays.asList(mapper.map(list, ProposalDTO[].class));
  }

  public Boolean checkProposalIsAbleToVote(Long id) {
    return repository.existsByIdAndResultAndCloseTimeGreaterThan(
            id,
            EResult.POLLING,
            LocalDateTime.now());
  }

  public void updateProposal(ProposalDTO proposalDTO) {
    Optional<Proposal> updatedEntity =
            repository.findById(proposalDTO.getId());
    if (updatedEntity.isPresent()) {
      updatedEntity.get().setApprove(proposalDTO.getApprove());
      updatedEntity.get().setReject(proposalDTO.getReject());
      updatedEntity.get().setResult(proposalDTO.getResult());
      repository.save(updatedEntity.get());
    }
  }

  public Boolean findProposalById(Long id) {
    return repository.existsById(id);
  }
}
