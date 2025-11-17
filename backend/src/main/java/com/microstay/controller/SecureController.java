package com.microstay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/secure")
public class SecureController {

    @GetMapping("/ping")
    public ResponseEntity<Map<String, Object>> ping(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(Map.of(
            "status", "ok",
            "subject", jwt.getSubject(),
            "issuer", String.valueOf(jwt.getIssuer())
        ));
    }
}


