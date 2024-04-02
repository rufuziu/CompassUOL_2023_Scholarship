package br.demattos.iury.msproposal.mqueue.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
  @Value("${mq.queues.voting-result}")
  private String votingResultQueue;
  @Bean
  public Queue queueVotingResult(){
    return new Queue(votingResultQueue,true);
  }
}
