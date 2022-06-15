package com.nhnacademy.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskNum;

    @ManyToOne
    @JoinColumn(name = "project_num")
    private Project project;

    @OneToOne
    @JoinColumn(name = "milestone_num")
    private Milestone milestone;

    @NotBlank
    private String taskTitle;

    @NotBlank
    private String taskContent;

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> comments;
}
