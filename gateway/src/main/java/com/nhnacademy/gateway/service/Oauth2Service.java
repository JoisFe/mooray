package com.nhnacademy.gateway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.gateway.auth.GithubProfile;
import com.nhnacademy.gateway.auth.OAuthToken;

public interface Oauth2Service {
    GithubProfile getGithubProfile(OAuthToken oAuthToken) throws JsonProcessingException;

    OAuthToken getOAuthToken(String code) throws JsonProcessingException;
}
