package com.nhnacademy.task.dto.respond;

import com.nhnacademy.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor  // entity 정보 projection위해 Allargsconstructor
@Data // JSON serializable --> @NoArgsConstructor만 있어도 되지 않나...
public class CommentRespondDto {
    private Long commentNum;

    private Task task;

    private String commentContent;

    private String writerId;
}
