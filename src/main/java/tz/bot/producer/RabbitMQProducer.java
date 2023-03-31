package tz.bot.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQProducer.class);

    @Value("{rabbitmq.exchange.name}")
    private String exchange;

    @Value("{rabbitmq.routing-key.name}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message){
        LOGGER.info(String.format("Message Sent -> %s",message));
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }


}
