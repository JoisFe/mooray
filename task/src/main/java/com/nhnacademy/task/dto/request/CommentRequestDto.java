package com.nhnacademy.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
public class CommentRequestDto {
    private String commentContent;
}
