package com.nhnacademy.task.repository;

import com.nhnacademy.task.dto.respond.CommentRespondDto;
import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CommentRespondDto> findByTask(Task task);
}
