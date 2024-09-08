package com.juyeon.board.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentDTO {
    private long commentId;
    private Long postId;
    private String content;
}