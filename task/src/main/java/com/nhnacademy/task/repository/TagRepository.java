package com.nhnacademy.task.repository;

import com.nhnacademy.task.dto.respond.TagRespondDto;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Tag;
import com.nhnacademy.task.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<TagRespondDto> findByProject(Project project);
}
