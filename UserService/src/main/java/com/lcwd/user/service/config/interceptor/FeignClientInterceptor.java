package com.lcwd.user.service.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OAuth2AuthorizedClientManager manager;

    private Logger logger = LoggerFactory.getLogger(FeignClientInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        OAuth2AuthorizedClient client = manager
                .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-client").principal("internal").build());
        String token = client == null ? "" : client.getAccessToken().getTokenValue();
        logger.info("Feign Client Interceptor Token: {}", token);
        template.header("Authorization", "Bearer " + token);
    }

}
