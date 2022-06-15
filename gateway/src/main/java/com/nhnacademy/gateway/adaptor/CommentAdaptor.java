package com.nhnacademy.gateway.adaptor;

import com.nhnacademy.gateway.domain.Comment;
import com.nhnacademy.gateway.dto.request.CommentRequestDto;
import java.util.List;

public interface CommentAdaptor {
    String registerComment(CommentRequestDto commentRequestDto, Long projectNum, Long taskNum,
                           String writerId);

    List<Comment> getCommentList(Long projectNum, Long taskNum);

    String updateComment(String commentContent, Long projectNum, Long taskNum, Long commentNum);

    String deleteComment(Long projectNum, Long taskNum, Long commentNum);
}
