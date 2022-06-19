package com.nhnacademy.task.service;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.nhnacademy.task.dto.respond.CommentRespondDto;
import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectState;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.repository.CommentRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.impl.CommentServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private CommentRepository commentRepository;

    @DisplayName("댓글 등록 성공")
    @Test
    void registerComment() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        Task task = Task.builder()
            .taskNum(100L)
            .taskTitle("태스크")
            .taskContent("태스크 내용")
            .project(project)
            .build();

        when(taskRepository.findById(task.getTaskNum())).thenReturn(Optional.of(task));

        Comment comment = Comment.builder()
            .commentContent("댓글입니다")
            .task(task)
            .writerId("jo")
            .build();

        when(commentRepository.save(any())).thenReturn(comment);

        assertThat(
            commentService.registerComment(comment.getCommentContent(), project.getProjectNum(),
                task.getTaskNum(), comment.getWriterId())).isEqualTo("댓글이 저장 되었습니다.");
    }

    @DisplayName("댓글 등록 실패")
    @Test
    void registerCommentFailTest() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        Task task = null;

        when(taskRepository.findById(any())).thenReturn(Optional.ofNullable(task));

        Comment comment = Comment.builder()
            .commentContent("댓글입니다")
            .task(task)
            .writerId("jo")
            .build();

        assertThat(
            commentService.registerComment(comment.getCommentContent(), project.getProjectNum(),
                any(), comment.getWriterId())).isEqualTo("해당 task가 존재하지 않습니다.");
    }

    @Test
    void getAllComment() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        Task task = Task.builder()
            .taskNum(100L)
            .taskTitle("태스크")
            .taskContent("태스크 내용")
            .project(project)
            .build();

        when(taskRepository.findById(task.getTaskNum())).thenReturn(Optional.of(task));

        List<CommentRespondDto> commentRespondDtos =
            List.of(CommentRespondDto.builder()
                .commentNum(1000L)
                .commentContent("호호호")
                .task(task)
                .build());

        when(commentRepository.findByTask(task)).thenReturn(commentRespondDtos);

        assertThat(
            commentService.getAllComment(project.getProjectNum(), task.getTaskNum())).isEqualTo(
            commentRespondDtos);
    }

    @DisplayName("comment 수정 성공")
    @Test
    void updateComment() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        Task task = Task.builder()
            .taskNum(100L)
            .taskTitle("태스크")
            .taskContent("태스크 내용")
            .project(project)
            .build();
        Comment comment = Comment.builder()
            .commentContent("댓글입니다")
            .task(task)
            .writerId("jo")
            .build();

        when(commentRepository.findById(comment.getCommentNum())).thenReturn(Optional.of(comment));

        comment.setCommentContent("수정한 댓글");

        when(commentRepository.save(any())).thenReturn(comment);

        assertThat(commentService.updateComment(comment.getCommentContent(), project.getProjectNum(),
            task.getTaskNum(), comment.getCommentNum())).isEqualTo("comment가 수정되었습니다.");
    }

    @DisplayName("comment 수정 실패")
    @Test
    void updateCommentFailTest() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        Task task = Task.builder()
            .taskNum(100L)
            .taskTitle("태스크")
            .taskContent("태스크 내용")
            .project(project)
            .build();
        Comment comment = null;

        when(commentRepository.findById(any())).thenReturn(Optional.ofNullable(comment));

        assertThat(
            commentService.updateComment("호호", project.getProjectNum(),
                task.getTaskNum(), any())).isEqualTo("해당 comment가 존재하지 않습니다.");
    }
    @Test
    void deleteComment() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        Task task = Task.builder()
            .taskNum(100L)
            .taskTitle("태스크")
            .taskContent("태스크 내용")
            .project(project)
            .build();
        Comment comment = Comment.builder()
            .commentNum(2L)
            .commentContent("댓글입니다")
            .task(task)
            .writerId("jo")
            .build();

        doNothing().when(commentRepository).deleteById(comment.getCommentNum());

        assertThat(commentService.deleteComment(project.getProjectNum(), task.getTaskNum(),
            comment.getCommentNum())).isEqualTo("comment가 삭제되었습니다.");
    }
}