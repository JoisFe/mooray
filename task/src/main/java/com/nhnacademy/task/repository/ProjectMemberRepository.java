package com.nhnacademy.task.repository;

import com.nhnacademy.task.dto.respond.ProjectMemberRespondDto;
import com.nhnacademy.task.entity.ProjectMember;
import com.nhnacademy.task.entity.pk.ProjectMemberPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberPk> {
    Page<ProjectMemberRespondDto> findByProjectMemberPkProjectMemberNum(Long memberNum, Pageable pageable);

    ProjectMemberRespondDto findByProjectMemberPkProjectNumAndProjectRole(Long projectNum, String projectRole);

}
