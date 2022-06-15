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
public class TaskTagPk implements Serializable {
    private Long tagNum;
    private Long taskNum;
}
