package com.nhnacademy.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.nhnacademy.task.dto.respond.TagRespondDto;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectState;
import com.nhnacademy.task.entity.Tag;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.service.impl.TagServiceImpl;
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
@Transactional
class TagServiceTest {
    @InjectMocks
    TagServiceImpl tagServiceImpl;

    @Mock
    TagRepository tagRepository;
    @Mock
    ProjectRepository projectRepository;

    @DisplayName("tag 생성 성공")
    @Test
    void createTag() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));

        Tag tag = Tag.builder()
            .tagTitle("태그 제목입니다")
            .project(project)
            .build();

        when(tagRepository.save(any())).thenReturn(tag);

        assertThat(tagServiceImpl.createTag(project.getProjectNum(), tag.getTagTitle())).isEqualTo(
            "tag가 저장되었습니다.");
    }

    @DisplayName("tag 생성 실패")
    @Test
    void createTagFailTest() {
        Project project = null;

        Tag tag = Tag.builder()
            .tagTitle("태그제목")
            .project(project)
            .build();

        when(projectRepository.findById(any())).thenReturn(
            Optional.ofNullable(project));

        assertThat(tagServiceImpl.createTag(any(), tag.getTagTitle())).isEqualTo(
            "해당 프로젝트가 존재하지 않습니다.");
    }

    @Test
    void findAllTag() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));

        List<TagRespondDto> tagRespondDtos = List.of(TagRespondDto.builder()
            .tagNum(100L)
            .tagTitle("태그 제목")
            .project(project)
            .build());


        when(tagRepository.findByProject(project)).thenReturn(tagRespondDtos);

        assertThat(tagServiceImpl.findAllTag(project.getProjectNum())).isEqualTo(tagRespondDtos);
    }

    @DisplayName("tag 수정 성공")
    @Test
    void updateTag() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Tag tag = Tag.builder()
            .tagNum(100L)
            .tagTitle("태그 제목입니다")
            .project(project)
            .build();

        when(tagRepository.findById(tag.getTagNum())).thenReturn(Optional.of(tag));

        tag.setTagTitle("변경된 태그 제목");

        when(tagRepository.save(any())).thenReturn(tag);

        assertThat(tagServiceImpl.updateTag(project.getProjectNum(), tag.getTagNum(),
            "변경된 태그 제목")).isEqualTo("해당 태그가 수정되었습니다.");
    }

    @DisplayName("tag 수정 실패")
    @Test
    void updateTagFailTest() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Tag tag = null;

        when(tagRepository.findById(any())).thenReturn(Optional.ofNullable(tag));

        assertThat(tagServiceImpl.updateTag(project.getProjectNum(), any(),
            "변경된 태그 제목")).isEqualTo("해당 tag가 존재하지 않습니다.");

    }

    @Test
    void deleteTag() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Tag tag = Tag.builder()
            .tagNum(100L)
            .tagTitle("태그 제목입니다")
            .project(project)
            .build();

        doNothing().when(tagRepository).deleteById(tag.getTagNum());

        assertThat(tagServiceImpl.deleteTag(project.getProjectNum(), tag.getTagNum())).isEqualTo(
            "해당 태그가 삭제되었습니다.");
    }

    @Test
    void getTagByProjectNum() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Tag tag = Tag.builder()
            .tagNum(100L)
            .tagTitle("태그 제목입니다")
            .project(project)
            .build();

        List<TagRespondDto> tagRespondDtos = List.of(TagRespondDto.builder()
            .tagNum(tag.getTagNum())
            .tagTitle(tag.getTagTitle())
            .project(project)
            .build());

        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));

        when(tagRepository.findByProject(project)).thenReturn(tagRespondDtos);

        assertThat(
            tagServiceImpl.getTagByProjectNum(project.getProjectNum(), tag.getTagNum())).isEqualTo(
            tagRespondDtos);
    }
}