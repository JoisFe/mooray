package com.nhnacademy.gateway.adaptor.impl;

import com.nhnacademy.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.gateway.config.DomainProperties;
import com.nhnacademy.gateway.domain.Task;
import com.nhnacademy.gateway.dto.request.TaskRequestDto;
import com.nhnacademy.gateway.exception.NotFoundTaskException;
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
public class TaskAdaptorImpl implements TaskAdaptor {
    private final RestTemplate restTemplate;

    private final HttpHeaders headers = new HttpHeaders();

    private final DomainProperties domainProperties;

    @Override
    public String makeTask(TaskRequestDto taskRequestDto,
                           Long projectNum, Long memberNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity(taskRequestDto, headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() + "/project/{projectNum}/task/create/{memberNum}",
            HttpMethod.POST,
            httpEntity,
            String.class,
            projectNum,
            memberNum
        );

        return responds.getBody();
    }

    @Override
    public Task findTask(Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Optional<Task>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<Optional<Task>> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() + "/project/{projectNum}/task/{taskNum}",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<Optional<Task>>() {
            },
            projectNum,
            taskNum
        );

        return responds.getBody()
            .orElseThrow(() -> new NotFoundTaskException("해당 태스크가 존재하지 않습니다."));
    }

    @Override
    public List<Task> findTaskAll(Long projectNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<List<Task>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<List<Task>> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() + "/project/{projectNum}/task",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<Task>>() {
            },
            projectNum
        );

        return responds.getBody();
    }

    @Override
    public String updateTask(TaskRequestDto taskRequestDto, Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity(taskRequestDto, headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() + "/project/{projectNum}/task/{taskNum}/update",
            HttpMethod.POST,
            httpEntity,
            String.class,
            projectNum,
            taskNum
        );

        return responds.getBody();
    }

    @Override
    public String deleteTask(Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() + "/project/{projectNum}/task/{taskNum}/delete",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            taskNum
        );

        return responds.getBody();
    }

    @Override
    public String registerMilestone(Long milestoneNum, Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() +
                "/project/{projectNum}/task/{taskNum}/milestone/{milestoneNum}/register",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            taskNum,
            milestoneNum
        );

        return responds.getBody();
    }

    @Override
    public String registerTag(Long tagNum, Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getTaskDomain() +
                "/project/{projectNum}/task/{taskNum}/tag/{tagNum}/register",
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
