package demo.Keycloak_LAB4_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AuditLogger auditLogger;

    public String generateReport() {
        String clientId = getClientId();
        auditLogger.log("generateReport", clientId, "/api/admin/report", "SUCCESS");
        return "Report generated for client: " + clientId;
    }

    private String getClientId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null) ? auth.getName() : "anonymous";
    }
}