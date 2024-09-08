package com.juyeon.board.service;

import com.juyeon.board.domain.dto.CommentDTO;
import com.juyeon.board.domain.entity.Comment;
import com.juyeon.board.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void registerComment(CommentDTO newComment) {
        Comment comment = Comment.builder()
                .postId(newComment.getPostId())
                .content(newComment.getContent())
                .build();

        commentRepository.save(comment);
    }

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

}
