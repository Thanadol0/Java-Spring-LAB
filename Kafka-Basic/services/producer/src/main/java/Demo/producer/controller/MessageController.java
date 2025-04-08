package Demo.producer.controller;

import Demo.producer.dto.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaTemplate<String, MessageRequest> kafkaTemplate;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody MessageRequest request) {
        kafkaTemplate.send("test-topic", request.sender(), request);
        return ResponseEntity.ok("Message sent to Kafka topic");
    }
}