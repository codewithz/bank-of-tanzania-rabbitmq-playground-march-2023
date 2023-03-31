package tz.bot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tz.bot.model.User;
import tz.bot.producer.RabbitMQJsonProducer;

@RestController
@RequestMapping("/api/v1/rabbitmq")
public class MessageJsonController {

    private RabbitMQJsonProducer rabbitMQJsonProducer;

    @Autowired
    public MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody  User user){
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("JSON Message sent to RabbitMQ");
    }
}
