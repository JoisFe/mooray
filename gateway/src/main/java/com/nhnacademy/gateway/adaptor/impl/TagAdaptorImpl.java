package com.nhnacademy.gateway.adaptor.impl;

import com.nhnacademy.gateway.adaptor.TagAdaptor;
import com.nhnacademy.gateway.config.DomainProperties;
import com.nhnacademy.gateway.domain.Milestone;
import com.nhnacademy.gateway.domain.Tag;
import java.util.List;
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
public class TagAdaptorImpl implements TagAdaptor {
    private final RestTemplate restTemplate;

    private final HttpHeaders headers = new HttpHeaders();

    private final DomainProperties domainProperties;

    @Override
    public String createTag(String tagTitle, Long projectNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() +
                "/project/{projectNum}/create?tagTitle={tagTitle}",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            tagTitle
        );

        return responds.getBody();
    }

    @Override
    public List<Tag> findAllTag(Long projectNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<List<Tag>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<List<Tag>> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() + "/project/{projectNum}/tag",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<Tag>>() {
            },
            projectNum
        );

        return responds.getBody();
    }

    @Override
    public String update(String tagTitle, Long projectNum, Long tagNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() +
                "/project/{projectNum}/tag/{tagNum}/register?tagTitle={tagTitle}",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            tagNum,
            tagTitle
        );

        return responds.getBody();
    }

    @Override
    public String delete(Long projectNum, Long tagNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() +
                "/project/{projectNum}/tag/{tagNum}/delete",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            tagNum
        );

        return responds.getBody();
    }

    @Override
    public List<Tag> findTagByProjectNum(Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<List<Tag>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<List<Tag>> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() +
                "/project/{projectNum}/task/{taskNum}/tag/select",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<Tag>>() {
            },
            projectNum,
            taskNum
        );

        return responds.getBody();
    }
}
