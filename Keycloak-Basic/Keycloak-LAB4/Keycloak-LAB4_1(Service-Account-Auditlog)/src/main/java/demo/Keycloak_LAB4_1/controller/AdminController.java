package demo.Keycloak_LAB4_1.controller;

import demo.Keycloak_LAB4_1.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/report")
    @PreAuthorize("hasRole('access-api')")
    public String generateReport() {
        return adminService.generateReport();
    }
}
