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

    @Transactional
    public void updateComment(long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다."));

        // 수정
        Comment updatedComment = Comment.builder()
                .commentId(comment.getCommentId())
                .postId(commentDTO.getPostId() != null ? commentDTO.getPostId() : comment.getPostId())
                .content(commentDTO.getContent() != null ? commentDTO.getContent() : comment.getContent())
                .build();

        // 저장
        commentRepository.save(updatedComment);
    }

    public boolean deleteComment(long commentId) {
        try {
            if (commentRepository.existsById(commentId)) {
                commentRepository.deleteById(commentId);
                return true; // 댓글 삭제 성공
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
