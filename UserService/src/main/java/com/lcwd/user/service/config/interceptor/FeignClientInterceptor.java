package com.lcwd.user.service.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    OAuth2AuthorizedClientManager manager;

    @Override
    public void apply(RequestTemplate template) {
        OAuth2AuthorizedClient client = manager
                .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-client").principal("internal").build());
        String token = client == null ? "" : client.getAccessToken().getTokenValue();
        template.header("Authorization", "Bearer " + token);
    }

}
