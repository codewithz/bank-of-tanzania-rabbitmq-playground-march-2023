package tz.bot.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tz.bot.model.User;

@Service
public class RabbitMQJsonProducer {

    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing-key.json.name}")
    private String jsonRoutingKey;

    RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        LOGGER.info(String.format("JSON Message Sent -> %s",user));
        rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
    }
}
