package demo.Keycloak_LAB4_1.repository;

import demo.Keycloak_LAB4_1.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
