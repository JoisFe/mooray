package com.nhnacademy.task.repository;

import com.nhnacademy.task.dto.respond.MilestoneRespondDto;
import com.nhnacademy.task.entity.Milestone;
import com.nhnacademy.task.entity.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<MilestoneRespondDto> findByProject(Project project);
}
