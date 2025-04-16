package demo.mockito_LAB6.kafka;

import demo.mockito_LAB6.model.UserEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;


    public UserProducer(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(UserEvent event){
        kafkaTemplate.send("user-events", event);
    }

}
