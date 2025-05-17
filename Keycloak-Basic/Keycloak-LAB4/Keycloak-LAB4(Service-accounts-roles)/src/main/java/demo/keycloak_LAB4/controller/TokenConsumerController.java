package demo.keycloak_LAB4.controller;

import demo.keycloak_LAB4.service.KeycloakTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenConsumerController {

    private final KeycloakTokenService tokenService;

    @Autowired
    public TokenConsumerController(KeycloakTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/token")
    public String getToken() {
        return tokenService.getAccessToken();
    }

}
