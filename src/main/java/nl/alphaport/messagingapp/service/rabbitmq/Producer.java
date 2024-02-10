package nl.alphaport.messagingapp.service.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.alphaport.messagingapp.config.Constants;
import nl.alphaport.messagingapp.dto.request.SyncProductRequest;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

/**
 * @author: Abolfazl Shahrad Shahri (abolfazl2112@gmail.com)
 * 2024-01-24   3:39 PM
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {

    @Value("${rabbitmq.exchange.name}")
    private String EXCHANGE_NAME;

    @Value("${rabbitmq.routing.json.key}")
    private String ROUTING_KEY;

    @Value("${rabbitmq.exchange.delay.name}")
    private String delayExchangeName;

    @Value("${rabbitmq.routing.delay.key}")
    private String delayRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void Send(SyncProductRequest queuePayload){
        log.info(String.format("Message send to rabbitMQ : %s",queuePayload.toString()));
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration(String.valueOf(10000));
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, queuePayload);
    }

     /**
     *
     * @param queuePayload
     * @param delayTimes
     */
    public void sendWithDelay(SyncProductRequest queuePayload, final long delayTimes) {
        log.info("Send <" + queuePayload + ">" + ", in time <"+ ZonedDateTime.now() +">");
        rabbitTemplate.convertAndSend(delayExchangeName, delayRoutingKey, queuePayload,
                message -> {
                    message.getMessageProperties().setHeader(Constants.X_DELAY, delayTimes);
                    return message;
                });
    }
}
