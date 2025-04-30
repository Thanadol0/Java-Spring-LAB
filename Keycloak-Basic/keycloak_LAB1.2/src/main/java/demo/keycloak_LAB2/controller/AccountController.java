package demo.keycloak_LAB2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/account")
    public String getUser(@AuthenticationPrincipal Jwt jwt) {
        return "USER account info for: " + jwt.getClaimAsString("preferred_username");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/account")
    public String getAdmin(@AuthenticationPrincipal Jwt jwt) {
        return "ADMIN account info for: " + jwt.getClaimAsString("preferred_username");
    }
}

