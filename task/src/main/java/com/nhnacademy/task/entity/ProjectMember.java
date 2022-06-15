package com.nhnacademy.task.entity;


import com.nhnacademy.task.entity.pk.ProjectMemberPk;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ProjectMember {
    @EmbeddedId
    private ProjectMemberPk projectMemberPk;

    @Enumerated(EnumType.STRING)
    private ProjectRole projectRole;

    @MapsId("projectNum")
    @ManyToOne
    @JoinColumn(name = "project_num")
    private Project project;
}
