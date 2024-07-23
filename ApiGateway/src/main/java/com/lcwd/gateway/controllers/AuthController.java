package com.lcwd.gateway.controllers;

import java.time.Instant;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.gateway.models.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user,
            Model model) {
        String email = user.getEmail();
        OAuth2AccessToken accessToken = client.getAccessToken();
        OAuth2RefreshToken refreshToken = client.getRefreshToken();
        Instant expiresAt = client.getAccessToken().getExpiresAt();
        List<String> authorities = user.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority()).toList();

        logger.info("User Email-id: ", email);

        // creating auth response object
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(email);
        authResponse.setAccessToken(accessToken.getTokenValue());
        authResponse.setRefreshToken(refreshToken == null ? null : refreshToken.getTokenValue());
        authResponse.setExpiresAt(expiresAt == null ? null : expiresAt.getEpochSecond());
        authResponse.setAuthorities(authorities);

        return ResponseEntity.ok(authResponse);
    }
}
