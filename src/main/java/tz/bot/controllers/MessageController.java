package tz.bot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tz.bot.producer.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1/rabbitmq")
public class MessageController {

    private RabbitMQProducer producer;

    @Autowired
    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        producer.send(message);
        return ResponseEntity.ok("Message sent to RabbitMQ");

    }


}
