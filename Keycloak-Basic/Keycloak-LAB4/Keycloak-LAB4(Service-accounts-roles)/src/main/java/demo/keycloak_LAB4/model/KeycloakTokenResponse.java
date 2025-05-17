package demo.keycloak_LAB4.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeycloakTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
