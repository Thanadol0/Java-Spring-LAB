package demo.keycloak_LAB2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableMethodSecurity // ✅ เปิดให้ใช้ @PreAuthorize ที่ controller ได้
public class SecurityConfig {

    // ✅ สร้าง JwtAuthenticationConverter แบบ custom เพื่อ map role จาก JWT → GrantedAuthority
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        // ✅ กำหนด custom logic ดึง role จาก claim realm_access.roles
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Map<String, Object> realmAccess = jwt.getClaim("realm_access"); // ดึง object "realm_access" ทั้งก้อน
            if (realmAccess == null || realmAccess.get("roles") == null) {
                return List.of(); // ถ้าไม่มี roles → ไม่อนุญาต
            }

            List<String> roles = (List<String>) realmAccess.get("roles"); // ดึง list ของ role เช่น ["USER"]
            return roles.stream()
                    .map(role -> "ROLE_" + role) // Spring ต้องการ prefix "ROLE_" เสมอ
                    .map(SimpleGrantedAuthority::new) // แปลงเป็น GrantedAuthority
                    .collect(Collectors.toList());
        });

        return converter;
    }

    // ✅ ฟิลเตอร์หลักที่กำหนดกฎการเข้าถึงแต่ละ path
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").hasRole("USER")  // ต้องมี ROLE_USER
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ต้องมี ROLE_ADMIN
                        .anyRequest().authenticated() // อื่นๆ ต้อง login แต่ไม่บังคับ role
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())) // ✅ ใช้ converter ที่เขียนเอง
                );
        return http.build();
    }
}
