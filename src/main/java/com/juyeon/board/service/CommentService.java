package com.juyeon.board.service;

import com.juyeon.board.domain.dto.CommentDTO;
import com.juyeon.board.domain.entity.Comment;
import com.juyeon.board.global.CommentNotFoundException;
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

    public Comment getCommentByCommentId(long commentId) throws CommentNotFoundException {
        Comment comment =  commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("존재하지 않는 댓글입니다."));

        return comment;
    }
}
