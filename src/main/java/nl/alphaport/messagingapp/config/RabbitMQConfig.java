package nl.alphaport.messagingapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Abolfazl Shahrad Shahri (abolfazl2112@gmail.com)
 * 2024-01-24   3:49 PM
 */
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueueName;
    @Value("${rabbitmq.queue.delay.name}")
    private String delayQueueName;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.exchange.delay.name}")
    private String delayExchangeName;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;
    @Value("${rabbitmq.routing.delay.key}")
    private String delayRoutingKey;

    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueueName);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    CustomExchange delayDirectExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put(Constants.X_DELAY_TYPE, Constants.QUEUE_DIRECT_TYPE);
        return new CustomExchange(delayExchangeName, Constants.X_DELAY_MESSAGE, true, false, args);
    }

    @Bean
    public Queue testDelayQueue() {
        return new Queue(delayQueueName);
    }

    /**
     * Bind delay queue to exchange
     */
    @Bean
    public Binding testDelayBinding() {
        return BindingBuilder
                .bind(testDelayQueue())
                .to(delayDirectExchange())
                .with(delayRoutingKey)
                .noargs();
    }
}
