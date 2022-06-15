package com.nhnacademy.gateway.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.gateway.auth.GithubProfile;
import com.nhnacademy.gateway.auth.OAuthToken;
import com.nhnacademy.gateway.service.Oauth2Service;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Getter
@Setter
@ConfigurationProperties(prefix = "oauth.github")
public class Oauth2ServiceImpl implements Oauth2Service {
    private String redirectUrl;
    private String tokenRequestUrl;
    private String profileRequestUrl;
    private String clientId;
    private String clientSecret;

    @Override
    public GithubProfile getGithubProfile(OAuthToken oAuthToken) throws JsonProcessingException {
        RestTemplate profileRequestTemplate = new RestTemplate();
        ResponseEntity<String> profileResponse = profileRequestTemplate.exchange(
            profileRequestUrl,
            HttpMethod.GET,
            getProfileRequestEntity(oAuthToken),
            String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(profileResponse.getBody(), GithubProfile.class);
    }

    private HttpEntity<MultiValueMap<String, String>> getProfileRequestEntity(OAuthToken oAuthToken) {
        HttpHeaders infoRequestHeaders = new HttpHeaders();
        infoRequestHeaders.add("Authorization", "token " + oAuthToken.getAccessToken());
        return new HttpEntity<>(infoRequestHeaders);
    }

    @Override
    public OAuthToken getOAuthToken(String code) throws JsonProcessingException {
        HttpEntity<MultiValueMap<String, String>> codeRequestHttpEntity = getCodeRequestHttpEntity(code);
        RestTemplate tokenRequestTemplate = new RestTemplate();
        ResponseEntity<String> response = tokenRequestTemplate.exchange(tokenRequestUrl,
            HttpMethod.POST,
            getCodeRequestHttpEntity(code),
            String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        return objectMapper.readValue(response.getBody(), OAuthToken.class);
    }

    private HttpEntity<MultiValueMap<String, String>> getCodeRequestHttpEntity(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("redirect_url", redirectUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        return new HttpEntity<>(params, headers);
    }
}
