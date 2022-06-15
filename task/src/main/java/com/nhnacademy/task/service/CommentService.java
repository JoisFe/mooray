package com.nhnacademy.task.service;

import com.nhnacademy.task.dto.respond.CommentRespondDto;
import java.util.List;

public interface CommentService {
    String registerComment(String commentContent, Long projectNum, Long taskNum, String wrtierId);

    List<CommentRespondDto> getAllComment(Long projectNum, Long taskNum);

    String updateComment(String commentContent, Long projectNum, Long taskNum, Long commentNum);

    String deleteComment(Long projectNum, Long taskNum, Long commentNum);
}
