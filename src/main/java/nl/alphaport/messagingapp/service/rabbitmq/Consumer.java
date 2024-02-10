package nl.alphaport.messagingapp.service.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import nl.alphaport.messagingapp.dto.request.SyncProductRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.concurrent.CountDownLatch;

/**
 * @author: Abolfazl Shahrad Shahri (abolfazl2112@gmail.com)
 * 2024-01-24   3:37 PM
 */
@Slf4j
@Service
public class Consumer {
    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(String message) {
        log.info("Received <" + message + ">" + ", counter <"+latch+">");
        latch.countDown();
    }

    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void receiveJsonMessage(SyncProductRequest message) {
        log.info("Received <" + message + ">" + ", counter <"+latch+">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    @RabbitListener(queues = "${rabbitmq.queue.delay.name}")
    public void receiveJsonMessageWithDelay(SyncProductRequest message) {
        log.info("Received <" + message + ">" + ", in time <"+ ZonedDateTime.now() +">");
        latch.countDown();
    }
}
