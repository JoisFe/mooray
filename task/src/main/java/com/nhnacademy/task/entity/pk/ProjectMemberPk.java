package com.nhnacademy.task.entity.pk;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Embeddable
public class ProjectMemberPk implements Serializable {
    private Long projectMemberNum;
    private Long projectNum;
}
