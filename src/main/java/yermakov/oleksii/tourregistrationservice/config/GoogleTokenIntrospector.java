package yermakov.oleksii.tourregistrationservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
@Component
public class GoogleTokenIntrospector implements OpaqueTokenIntrospector {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        try {
            String introspectionUri = "https://oauth2.googleapis.com/tokeninfo?access_token=" + token;
            Map<String, Object> response = this.restTemplate.getForObject(introspectionUri, Map.class);

            if (response == null || response.containsKey("error")) {
                throw new OAuth2AuthenticationException(new OAuth2Error("invalid_token", "Invalid token", null));
            }

            // Create OidcUserInfo
            OidcUserInfo userInfo = new OidcUserInfo(response);

            // Convert expiration time from seconds since epoch to Instant
            long exp = Long.parseLong(response.get("exp").toString());
            Instant expirationTime = Instant.ofEpochSecond(exp);

            // Extract necessary claims for OidcIdToken
            Map<String, Object> claims = Map.of(
                    "sub", response.get("sub"),
                    "aud", response.get("aud"),
                    "azp", response.get("azp"),
                    "scope", response.get("scope"),
                    "exp", expirationTime,
                    "expires_in", response.get("expires_in"),
                    "email", response.get("email"),
                    "email_verified", response.get("email_verified"),
                    "access_type", response.get("access_type")
            );

            // Create OidcIdToken
            OidcIdToken idToken = new OidcIdToken(token, Instant.now(), expirationTime, claims);

            // Create authorities (you may need to customize this based on your needs)
            List<GrantedAuthority> authorities = Collections.emptyList();

            // Return DefaultOidcUser
            return new DefaultOidcUser(authorities, idToken, userInfo);
        } catch (HttpClientErrorException e) {
            throw new OAuth2AuthenticationException(new OAuth2Error("invalid_token", "Invalid token", null), e);
        }
    }}