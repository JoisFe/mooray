package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.dto.respond.TagRespondDto;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Tag;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.service.TagService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public String createTag(Long projectNum, String tagTitle) {
        Optional<Project> project = projectRepository.findById(projectNum);

        if (project.isEmpty()) {
            return "해당 프로젝트가 존재하지 않습니다.";
        }

        Tag tag = Tag.builder()
            .tagTitle(tagTitle)
            .project(project.get())
            .build();

        tagRepository.save(tag);

        return "tag가 저장되었습니다.";
    }

    @Override
    public List<TagRespondDto> findAllTag(Long projectNum) {
        Project project = projectRepository.findById(projectNum)
            .orElseThrow(() -> new RuntimeException("해당 프로젝트가 존재하지 않습니다."));
        return tagRepository.findByProject(project);
    }

    @Transactional
    @Override
    public String updateTag(Long projectNum, Long tagNum, String tagTitle) {
        Optional<Tag> tag = tagRepository.findById(tagNum);

        if (tag.isEmpty()) {
            return "해당 tag가 존재하지 않습니다.";
        }

        Tag updateTag = tag.get();
        updateTag.setTagTitle(tagTitle);

        tagRepository.save(updateTag);

        return "해당 태그가 수정되었습니다.";
    }

    @Transactional
    @Override
    public String deleteTag(Long projectNum, Long tagNum) {
        tagRepository.deleteById(tagNum);

        return "해당 태그가 삭제되었습니다.";
    }

    @Override
    public List<TagRespondDto> getTagByProjectNum(Long projectNum, Long taskNum) {
        Project project = projectRepository.findById(projectNum)
            .orElseThrow(() -> new RuntimeException("해당 프로젝트가 존재하지 않습니다."));
        return tagRepository.findByProject(project);
    }
}
