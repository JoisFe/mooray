package com.nhnacademy.gateway.adaptor.impl;

import com.nhnacademy.gateway.adaptor.TaskTagAdaptor;
import com.nhnacademy.gateway.config.DomainProperties;
import com.nhnacademy.gateway.domain.Task;
import com.nhnacademy.gateway.domain.TaskTag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class TaskTagAdaptorImpl implements TaskTagAdaptor {
    private final RestTemplate restTemplate;

    private final HttpHeaders headers = new HttpHeaders();

    private final DomainProperties domainProperties;

    @Override
    public List<String> getTaskTagList(Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<List<String>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<List<String>> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() + "/project/{projectNum}/task/{taskNum}/taskTag",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<String>>() {
            },
            projectNum,
            taskNum
        );

        return responds.getBody();
    }

    @Override
    public String registerTaskTag(Long tagNum, Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() + "/project/{projectNum}/task/{taskNum}/tag/{tagNum}/taskTag/register",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            taskNum,
            tagNum
        );

        return responds.getBody();
    }
}
