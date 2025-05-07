package demo.keycloak_LAB3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout(); // ✅ ลบ session Spring Security
        return "redirect:" + getKeycloakLogoutUrl(); // ✅ ไปยัง Keycloak logout URL
    }

    private String getKeycloakLogoutUrl() {
        String keycloakLogoutEndpoint = "http://localhost:8080/realms/BankRealm/protocol/openid-connect/logout";
        String redirectUriAfterLogout = "http://localhost:8081/"; // ✅ หน้าเว็บที่จะกลับมาหลัง logout
        return keycloakLogoutEndpoint + "?redirect_uri=" + redirectUriAfterLogout;
    }
}
