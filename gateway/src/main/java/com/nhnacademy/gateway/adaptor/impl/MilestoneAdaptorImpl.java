package com.nhnacademy.gateway.adaptor.impl;

import com.nhnacademy.gateway.adaptor.MilestoneAdaptor;
import com.nhnacademy.gateway.config.DomainProperties;
import com.nhnacademy.gateway.domain.Milestone;
import com.nhnacademy.gateway.domain.TaskTag;
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
public class MilestoneAdaptorImpl implements MilestoneAdaptor {
    private final RestTemplate restTemplate;

    private final HttpHeaders headers = new HttpHeaders();

    private final DomainProperties domainProperties;

    @Override
    public String createMilestone(String milestoneTitle, Long projectNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() +
                "/project/{projectNum}/milestone/create?milestoneTitle={milestoneTitle}",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            milestoneTitle
        );

        return responds.getBody();
    }

    @Override
    public List<Milestone> findAllMilestone(Long projectNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<List<Milestone>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<List<Milestone>> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() + "/project/{projectNum}/milestone",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<Milestone>>() {
            },
            projectNum
        );

        return responds.getBody();
    }

    @Override
    public String update(String milestoneTitle, Long projectNum, Long milestoneNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() +
                "/project/{projectNum}/milestone/{milestoneNum}/register?milestoneTitle={milestoneTitle}",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            milestoneNum,
            milestoneTitle
        );

        return responds.getBody();
    }

    @Override
    public String delete(Long projectNum, Long milestoneNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() +
                "/project/{projectNum}/milestone/{milestoneNum}/delete",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            milestoneNum
        );

        return responds.getBody();
    }

    @Override
    public List<Milestone> findMilestoneByProjectNum(Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<List<Milestone>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<List<Milestone>> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() +
                "/project/{projectNum}/task/{taskNum}/milestone/select",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<Milestone>>() {
            },
            projectNum,
            taskNum
        );

        return responds.getBody();
    }

    @Override
    public String findMilestoneByTaskNum(Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() + "/project/{projectNum}/task/{taskNum}/milestone",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            taskNum
        );

        return responds.getBody();
    }
}
