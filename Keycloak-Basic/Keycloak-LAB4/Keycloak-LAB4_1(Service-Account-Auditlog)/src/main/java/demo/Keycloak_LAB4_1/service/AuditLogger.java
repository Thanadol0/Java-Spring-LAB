package demo.Keycloak_LAB4_1.service;

import demo.Keycloak_LAB4_1.model.AuditLog;
import demo.Keycloak_LAB4_1.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuditLogger {

    private final AuditLogRepository auditLogRepository;

    public void log(String action, String clientId, String endpoint, String result) {
        AuditLog logEntry = new AuditLog(null, clientId, action, endpoint, result, Instant.now());
        auditLogRepository.save(logEntry);
        log.info("AUDIT | clientId={} | action={} | endpoint={} | result={}", clientId, action, endpoint, result);
    }
}
