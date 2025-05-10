package demo.keycloak_LAB3.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    // [Optional] สำหรับใช้ logout แบบ manual (ไม่ได้ใช้งานใน LAB นี้)
    @GetMapping("/logout-real")
    public String logout(HttpServletRequest request,
                         @RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient)
            throws ServletException {

        request.logout(); // invalidate session ของ Spring

        // ใช้ OidcUser เพื่อดึง id_token จาก Principal
        OidcUser oidcUser = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String idTokenHint = oidcUser.getIdToken().getTokenValue();

        // URL logout ของ Keycloak
        String logoutUrl = "http://localhost:8080/realms/BankRealm/protocol/openid-connect/logout"
                + "?id_token_hint=" + idTokenHint
                + "&post_logout_redirect_uri=http://localhost:8081/";

        return "redirect:" + logoutUrl;
    }



}
