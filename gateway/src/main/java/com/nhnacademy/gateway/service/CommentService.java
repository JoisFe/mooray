package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.domain.Comment;
import com.nhnacademy.gateway.dto.request.CommentRequestDto;
import java.util.List;

public interface CommentService {
    String registerComment(CommentRequestDto commentRequestDto, Long projectNum, Long taskNum,
                           String writerId);

    List<Comment> getCommentList(Long projectNum, Long taskNum);

    String update(String commentContent, Long projectNum, Long taskNum, Long commentNum);

    String delete(Long projectNum, Long taskNum, Long commentNum);
}

