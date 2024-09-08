package com.juyeon.board.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long commentId;

//    @JoinColumn(name = "post_id")
//    @JsonBackReference
//    @ManyToOne  // bidirectional
//    private Post post;

//    @JoinColumn(name = "post_id")
    private long postId;

    private String content;
}