package br.demattos.iury.msproposal.repositories;

import br.demattos.iury.msproposal.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
  Boolean existsByProposalIdAndEmployeeCpf(Long proposalId,
                                           String employeeCpf);
}
