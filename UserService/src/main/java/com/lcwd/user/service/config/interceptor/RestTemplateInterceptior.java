package com.lcwd.user.service.config.interceptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

public class RestTemplateInterceptior implements ClientHttpRequestInterceptor {

    private OAuth2AuthorizedClientManager manager;

    public RestTemplateInterceptior(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
    }

    @Override
    public @NonNull ClientHttpResponse intercept(
            @NonNull HttpRequest request,
            @NonNull byte[] body,
            @NonNull ClientHttpRequestExecution execution)
            throws IOException {
        OAuth2AuthorizedClient client = manager
                .authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-client").principal("internal").build());
        if (client == null) {
            throw new IOException("Authorization failed: OAuth2Authorized client is null");
        }
        String token = client.getAccessToken().getTokenValue();
        request.getHeaders().add("Authorization", "Bearer " + token);
        return execution.execute(request, body);
    }

}
