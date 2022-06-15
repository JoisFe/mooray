package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.adaptor.CommentAdaptor;
import com.nhnacademy.gateway.domain.Comment;
import com.nhnacademy.gateway.dto.request.CommentRequestDto;
import com.nhnacademy.gateway.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentAdaptor commentAdaptor;

    @Override
    public String registerComment(CommentRequestDto commentRequestDto, Long projectNum, Long taskNum,
                                  String writerId) {
        return commentAdaptor.registerComment(commentRequestDto, projectNum, taskNum, writerId);
    }

    @Override
    public List<Comment> getCommentList(Long projectNum, Long taskNum) {
        return commentAdaptor.getCommentList(projectNum, taskNum);
    }

    @Override
    public String update(String commentContent, Long projectNum, Long taskNum, Long commentNum) {
        return commentAdaptor.updateComment(commentContent, projectNum, taskNum, commentNum);
    }

    @Override
    public String delete(Long projectNum, Long taskNum, Long commentNum) {
        return commentAdaptor.deleteComment(projectNum, taskNum, commentNum);
    }
}
