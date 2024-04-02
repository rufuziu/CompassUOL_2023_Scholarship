package br.demattos.iury.msproposal.mqueue;

import br.demattos.iury.msproposal.dtos.ProposalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProposalResultPublisher {
  private final RabbitTemplate rabbitTemplate;
  private final Queue queueVotingResult;
  private final Logger log = LoggerFactory
          .getLogger(ProposalResultPublisher.class);

  public ProposalResultPublisher(RabbitTemplate rabbitTemplate,
                                 Queue queueVotingResult) {
    this.rabbitTemplate = rabbitTemplate;
    this.queueVotingResult = queueVotingResult;
  }

  public void SendProposalResult(ProposalDTO proposalDTO) {
    try {
      String message = new StringBuilder()
              .append("[RESULTADO] PropostaId: ")
              .append(proposalDTO.getId())
              .append(" Descrição: ")
              .append(proposalDTO.getDescription())
              .append(" Aprovam: ")
              .append(proposalDTO.getApprove())
              .append(" Rejeitam: ")
              .append(proposalDTO.getReject())
              .append(" Resultado: ")
              .append(proposalDTO.getResult())
              .toString();
      rabbitTemplate.convertAndSend(queueVotingResult.getName(), message);
    }catch (Exception e){
      log.error(e.getMessage());
    }
  }
}
