package demo.mockito_LAB6;

import demo.mockito_LAB6.kafka.UserConsumer;
import demo.mockito_LAB6.model.UserEvent;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, topics = "user-events", brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventKafkaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserConsumer consumer;

    @Test
    void testKafkaFlow_FullIntegration() throws Exception {
        String body = "{\"userId\":\"U01\", \"action\":\"login\"}";

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string("sent"));

        // รอ Kafka process สักครู่
        Awaitility.await().atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> {
                    List<UserEvent> events = consumer.getConsumedEvents();
                    assertEquals(1, events.size());
                    assertEquals("U01", events.get(0).getUserId());
                    assertEquals("login", events.get(0).getAction());
                });
    }
}
