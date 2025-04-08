package Demo.consumer_service.consumer;

import Demo.producer.dto.MessageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "test-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(MessageRequest message) {
        System.out.printf("Received from %s: %s%n", message.sender(), message.message());
    }

}
