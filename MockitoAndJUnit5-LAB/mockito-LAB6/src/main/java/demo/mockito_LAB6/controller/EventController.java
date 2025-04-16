package demo.mockito_LAB6.controller;

import demo.mockito_LAB6.kafka.UserProducer;
import demo.mockito_LAB6.model.UserEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

   private final UserProducer producer;


   public EventController(UserProducer producer) {
        this.producer = producer;
   }

   @PostMapping
    public ResponseEntity<String> send(@RequestBody UserEvent event) {
       producer.send(event);
       return ResponseEntity.ok("sent");
   }

}
