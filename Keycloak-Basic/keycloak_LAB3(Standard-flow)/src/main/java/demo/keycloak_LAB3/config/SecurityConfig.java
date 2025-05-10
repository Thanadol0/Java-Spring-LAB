package demo.keycloak_LAB3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           ClientRegistrationRepository clients) throws Exception {

        // ★ Handler ที่จะใช้ตอน logout

        OidcClientInitiatedLogoutSuccessHandler oidcLogoutHandler =
                new OidcClientInitiatedLogoutSuccessHandler(clients);

        // กำหนดปลายทางหลัง logout (Keycloak จะ redirect มาให้)

        oidcLogoutHandler.setPostLogoutRedirectUri("{baseUrl}/");

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/home", true))
                .logout(logout -> logout
                        .logoutSuccessHandler(oidcLogoutHandler)   // <-- ใส่ Handler
                );

        return http.build();
    }
}
