package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.dto.respond.CommentRespondDto;
import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.repository.CommentRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.CommentService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Transactional
    @Override
    public String registerComment(@RequestBody String commentContent, Long projectNum, Long taskNum,
                                  String writerId) {
        Optional<Task> task = taskRepository.findById(taskNum);

        if (task.isEmpty()) {
            return "해당 task가 존재하지 않습니다.";
        }
        Comment comment = Comment.builder()
            .commentContent(commentContent)
            .task(task.get())
            .writerId(writerId)
            .build();

        commentRepository.save(comment);

        return "댓글이 저장 되었습니다.";
    }

    @Override
    public List<CommentRespondDto> getAllComment(Long projectNum, Long taskNum) {
        Task task = taskRepository.findById(taskNum)
            .orElseThrow(() -> new RuntimeException("해당 task가 존재하지 않습니다."));

        return commentRepository.findByTask(task);
    }

    @Transactional
    @Override
    public String updateComment(String commentContent, Long projectNum, Long taskNum,
                                Long commentNum) {
        Optional<Comment> comment = commentRepository.findById(commentNum);
        if (comment.isEmpty()) {
            return "해당 comment가 존재하지 않습니다.";
        }

        Comment updateComment = comment.get();
        updateComment.setCommentContent(commentContent);

        commentRepository.save(updateComment);

        return "comment가 수정되었습니다.";
    }

    @Transactional
    @Override
    public String deleteComment(Long projectNum, Long taskNum, Long commentNum) {
        commentRepository.deleteById(commentNum);

        return "comment가 삭제되었습니다.";
    }
}
