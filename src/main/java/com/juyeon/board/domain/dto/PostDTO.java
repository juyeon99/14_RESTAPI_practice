package com.juyeon.board.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PostDTO {
    private long postId;
    private String title;
    private String content;
}