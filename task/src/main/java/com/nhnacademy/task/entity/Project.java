package com.nhnacademy.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectNum;

    @NotBlank
    private String projectName;

    private String projectDescription;

    @Enumerated(EnumType.STRING)
    private ProjectState projectState;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Task> tasks;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<ProjectMember> projectMembers;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Tag> tags;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Milestone> milestones;
}
