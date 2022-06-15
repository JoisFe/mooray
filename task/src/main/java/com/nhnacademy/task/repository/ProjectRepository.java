package com.nhnacademy.task.repository;

import com.nhnacademy.task.dto.respond.ProjectRespondDto;
import com.nhnacademy.task.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<ProjectRespondDto> findAllBy(Pageable pageable);

    ProjectRespondDto findByProjectNum(Long projectNum);
}
