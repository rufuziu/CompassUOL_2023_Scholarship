package br.demattos.iury.msvoting.mqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProposalResultSubscriber {
  private final Logger log = LoggerFactory
          .getLogger(ProposalResultSubscriber.class);

  @RabbitListener(queues = "${mq.queue.voting-result}")
  public void recieveProposalResult(
          @Payload
          String payload) {
    try {
      log.info(payload);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
