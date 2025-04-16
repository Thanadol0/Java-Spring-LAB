package demo.mockito_LAB6.kafka;

import demo.mockito_LAB6.model.UserEvent;
import lombok.Getter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class UserConsumer {

    private final List<UserEvent> consumedEvents = new ArrayList<>();

    @KafkaListener(topics = "user-events",groupId = "test-group")
    public void listen(UserEvent event){
        consumedEvents.add(
                event
        );
    }

}
