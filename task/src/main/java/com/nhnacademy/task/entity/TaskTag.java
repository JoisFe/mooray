package com.nhnacademy.task.entity;

import com.nhnacademy.task.entity.pk.TaskTagPk;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class TaskTag {
    @EmbeddedId
    private TaskTagPk taskTagPk;

    @MapsId("tagNum")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_num")
    private Tag tag;

    @MapsId("taskNum")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_num")
    private Task task;
}
