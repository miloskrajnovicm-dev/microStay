package com.microstay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/hotels").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/hotels/**").permitAll()
                // Javni GET za hotels
                .requestMatchers(HttpMethod.GET, "/api/hotels", "/api/hotels/*").permitAll()
                .requestMatchers(
                    "/actuator/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/public/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
        return http.build();
    }

    /**
     * Maps Keycloak realm roles and client roles to Spring Security authorities with the "ROLE_" prefix.
     * Supports roles in:
     * - realm_access.roles -> e.g., ["ADMIN","HOTEL"]
     * - resource_access["microstay-backend"].roles -> e.g., ["ROLE_ADMIN","ROLE_HOTEL","ADMIN","HOTEL"]
     */
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(keycloakRolesConverter("microstay-backend"));
        return converter;
    }

    private Converter<Jwt, Collection<GrantedAuthority>> keycloakRolesConverter(String clientId) {
        return (Jwt jwt) -> {
            Set<String> roles = new HashSet<>();

            Object realmAccessObj = jwt.getClaim("realm_access");
            if (realmAccessObj instanceof Map) {
                Object realmRolesObj = ((Map<?, ?>) realmAccessObj).get("roles");
                if (realmRolesObj instanceof Collection) {
                    for (Object role : (Collection<?>) realmRolesObj) {
                        if (role instanceof String) {
                            roles.add((String) role);
                        }
                    }
                }
            }

            Object resourceAccessObj = jwt.getClaim("resource_access");
            if (resourceAccessObj instanceof Map && clientId != null) {
                Object clientAccessObj = ((Map<?, ?>) resourceAccessObj).get(clientId);
                if (clientAccessObj instanceof Map) {
                    Object clientRolesObj = ((Map<?, ?>) clientAccessObj).get("roles");
                    if (clientRolesObj instanceof Collection) {
                        for (Object role : (Collection<?>) clientRolesObj) {
                            if (role instanceof String) {
                                roles.add((String) role);
                            }
                        }
                    }
                }
            }

            if (roles.isEmpty()) {
                return Collections.emptyList();
            }

            return roles.stream()
                .map(r -> r.startsWith("ROLE_") ? r.substring("ROLE_".length()) : r)
                .map(r -> "ROLE_" + r)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        };
    }
}



