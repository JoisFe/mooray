package com.nhnacademy.gateway.adaptor.impl;

import com.nhnacademy.gateway.adaptor.CommentAdaptor;
import com.nhnacademy.gateway.config.DomainProperties;
import com.nhnacademy.gateway.domain.Comment;
import com.nhnacademy.gateway.domain.Member;
import com.nhnacademy.gateway.dto.request.CommentRequestDto;
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
public class CommentAdaptorImpl implements CommentAdaptor {
    private final RestTemplate restTemplate;

    private final HttpHeaders headers = new HttpHeaders();

    private final DomainProperties domainProperties;


    @Override
    public String registerComment(CommentRequestDto commentRequestDto, Long projectNum, Long taskNum,
                                  String writerId) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getGatewaypassDomain() +
                "/project/{projectNum}/task/{taskNum}/comment/register?writerId={writerId}&commentContent={commentContent}",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            taskNum,
            writerId,
            commentRequestDto.getCommentContent()
        );

        return responds.getBody();
    }

    @Override
    public List<Comment> getCommentList(Long projectNum, Long taskNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<List<Comment>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<List<Comment>> responds = restTemplate.exchange(
            domainProperties.getGatewaypassDomain() +
                "/project/{projectNum}/task/{taskNum}/comment/all",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<Comment>>() {},
            projectNum,
            taskNum
        );

        return responds.getBody();
    }

    @Override
    public String updateComment(String commentContent, Long projectNum, Long taskNum,
                                Long commentNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getGatewaypassDomain() +
                "/project/{projectNum}/task/{taskNum}/comment/{commentNum}/update?commentContent={commentContent}",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            taskNum,
            commentNum,
            commentContent
        );

        return responds.getBody();
    }

    @Override
    public String deleteComment(Long projectNum, Long taskNum, Long commentNum) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getGatewaypassDomain() +
                "/project/{projectNum}/task/{taskNum}/comment/{commentNum}/delete",
            HttpMethod.GET,
            httpEntity,
            String.class,
            projectNum,
            taskNum,
            commentNum
        );

        return responds.getBody();
    }
}
